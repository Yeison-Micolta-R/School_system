import { formatValue } from "./tableUtils"; 
import DynamicFormm from "../components/DynamicFormm";    
/**
 * Renderiza los detalles de un objeto (profesor, estudiante, etc.)
 * @param {object} item - Objeto con los datos
 * @param {string} title - Título de la sección
 * @param {function} onClose - Función para cerrar la sección
 */
export const renderDetail = (item, title, onClose, onEdit, onDelete) => {
    if (!item) return null;

    return (
        <div style={{ marginTop: "20px", border: "1px solid #ccc", padding: "10px", maxWidth: "400px" }}>
            <h3>{"Información del" + title}</h3>
            {Object.entries(item).map(([key, value]) => (
                <p key={key}>
                    <strong>{formatLabel(key)}:</strong> {formatValue(value)}
                </p>
            ))}
            <button onClick={onClose}>Cerrar</button>
            <button onClick={onEdit} >Editar </button>
            <button onClick={() => onDelete()}> Eliminar </button>

           
        </div>
    );
};

// Formatea la clave para mostrarla más legible (camelCase → con espacios)
const formatLabel = (label) => {
    return label
        .replace(/([A-Z])/g, " $1")
        .replace(/^./, (str) => str.toUpperCase());
};


export const Dynamic = ({ data, endpoint, onSuccess }) => {
  if (!data) return null;

  // 1. Extraemos las llaves (nombres de campos) para los inputs
  const keys = Object.keys(data).filter((k) => k !== "id");

  // 2. IMPORTANTE: Si el objeto NO tiene ID, es una creación.
  // Si TIENE ID, es una edición.
  console.log(data.id ? `Modo: EDITAR (ID: ${data.id})` : "Modo: CREAR");

  return (
    <DynamicFormm
      endpoint={endpoint}
      objectKeys={keys}
      onSuccess={onSuccess}
      initialData={data} // Pasamos el objeto tal cual
    />
  );
};

 export const getEmptyUser = (rol) => {
  console.log("Generando usuario vacío para:", rol);
    if (rol.length > 0) {
      return Object.keys(rol[0]).reduce((acc, key) => {
        if (key !== "id") acc[key] = "";
        return acc;
      }, {});
    }
    return { nombreCompleto: "", numeroDocumento: "" }; // Fallback
  };
  export const logout = () => {
    localStorage.clear();
    console.log("Cerrando sesión y redirigiendo al login...");
    // .replace() borra el historial para que no pueda volver atrás
    window.location.replace("/login"); 
};