import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
  const [usuario, setUsuario] = useState("");
  const [contrasena, setContrasena] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const res = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
       
        body: JSON.stringify({ usuario, contrasena }),
      });

      if (!res.ok) throw new Error("Credenciales incorrectas");

      const data = await res.json();
      console.log("Respuesta del backend:", data);
      // Guardar token si lo envía el backend

      localStorage.setItem("user", JSON.stringify(data));

      // Redirigir según el rol
      if (data.rol === "Administrador") {
        navigate("/admin");
      } else if (data.rol === "Estudiante") {
        navigate("/student");
      } else if (data.rol === "Profesor") {
        console.log("Navegando a /Teacher");
        navigate("/Teacher");
      } else if (data.rol === "Secretaria") {
        navigate("/secretaria");
      }

    } catch (err) {
      setError("Usuario o contraseña incorrectos");
    }
  };

  return (
    <form onSubmit={handleLogin}>
      <input
        type="text"
        placeholder="Usuario"
        value={usuario}
        onChange={(e) => setUsuario(e.target.value)}
      />
      <input
        type="password"
        placeholder="Contraseña"
        value={contrasena}
        onChange={(e) => setContrasena(e.target.value)}
      />
      <button type="submit">Ingresar</button>
      {error && <p style={{ color: "red" }}>{error}</p>}
    </form>
  );
}

export default Login;