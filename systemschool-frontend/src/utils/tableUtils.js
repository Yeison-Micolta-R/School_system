// Obtener columnas dinámicamente
export const getColumns = (data) => {
  if (!data || data.length === 0) return [];
  return Object.keys(data[0]);
};
 export const keyboardHandler = (data) => {

       console.log("keyboardHandler", Object.keys(data));
  return Object.keys(data);

  };
// Obtener valores de una fila
export const getRowValues = (row) => {
  return Object.values(row);
};

export const formatValue = (value) => {
  if (value === null || value === undefined) return "-";

  // Booleanos
  if (typeof value === "boolean") {
    return value ? "Activo" : "Inactivo";
  }

  // Objetos (teacher / student)
  if (typeof value === "object") {
    // Caso Profesor o Estudiante
    if (value.nombre && value.apellido) {
      return `${value.nombre} ${value.apellido}`;
    }

    // Cualquier otro objeto
    return "Relacionado";
  }
  return value;
};
export const getFormFields = (obj, excluded = []) => {
  //if (!obj || typeof obj !== "object") return [];

  return Object.keys(obj).filter(
    (key) => !excluded.includes(key)
  );
};

