import { useState, useEffect } from "react";
import { apiRequest } from "../services/api";

// 1. Agregamos la prop 'initialData'
function DynamicFormm({ endpoint, objectKeys, onSuccess, initialData = null }) {
  const [formData, setFormData] = useState({});

  // 2. Sincronizar el formulario con los datos recibidos (si es edición)
  useEffect(() => {
    if (initialData) {
      setFormData(initialData);
    } else {
      // Si es creación, inicializamos campos vacíos para evitar errores de React
      const emptyFields = objectKeys.reduce((acc, key) => ({ ...acc, [key]: "" }), {});
      setFormData(emptyFields);
    }
  }, [initialData, objectKeys]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({
      ...formData,
      // 3. Manejo de booleanos para evitar el error de "Tumaco"
      [name]: type === "checkbox" ? checked : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    // 4. Lógica de método: Si hay ID es PUT (Editar), si no es POST (Crear)
    const isEditing = !!formData.id;
    const method = isEditing ? "PUT" : "POST";
    const finalEndpoint = isEditing ? `${endpoint}/${formData.id}` : endpoint;

    try {
      await apiRequest(finalEndpoint, method, formData);
      onSuccess?.();
      alert(isEditing ? "Actualizado correctamente" : "Creado correctamente");
    } catch (err) {
      alert("Error al procesar la solicitud");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h3>{formData.id ? "Editar Registro" : "Nuevo Registro"}</h3>

      {objectKeys.map((key) => (
        <div key={key} style={{ marginBottom: "8px" }}>
          <label style={{ display: "block", textTransform: "capitalize" }}>{key}</label>
          <input
            name={key}
            // 5. Soporte para Checkbox si el valor es booleano
            type={typeof formData[key] === "boolean" ? "checkbox" : "text"}
            value={typeof formData[key] === "boolean" ? undefined : (formData[key] || "")}
            checked={typeof formData[key] === "boolean" ? formData[key] : undefined}
            onChange={handleChange}
            style={{ width: typeof formData[key] === "boolean" ? "auto" : "100%" }}
          />
        </div>
      ))}

      <div style={{ marginTop: "15px" }}>
        <button type="submit">{formData.id ? "Actualizar" : "Guardar"}</button>
      </div>
    </form>
  );
}

export default DynamicFormm;
