const BASE_URL = "http://localhost:8080";

const getToken = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  return user?.token;
};

export const apiGet = async (endpoint) => {
  if (!endpoint) {
    throw new Error("Endpoint no definido");
  }

  const token = getToken();

  if (!token) {
    throw new Error("Usuario no autenticado");
  }

  const response = await fetch(`${BASE_URL}${endpoint}`, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
    },
  });

  if (!response.ok) {
    throw new Error(`Error ${response.status} al consultar ${endpoint}`);
  }

  return await response.json();
};


export const apiRequest = async (endpoint, method = "GET", data = null) => {
  const token = getToken();
  const options = {
    method,
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
    },
  };

  if (data) {
    options.body = JSON.stringify(data);
  }

  const res = await fetch(`${BASE_URL}${endpoint}`, options);

  if (!res.ok) throw new Error("Error en la petición");

  // ✅ CORRECCIÓN: Verificar si hay contenido antes de hacer .json()
  // El código 204 significa "No Content"
  if (res.status === 204 || res.headers.get("content-length") === "0") {
    return null; 
  }

  return res.json();
};
