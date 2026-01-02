import { useState } from "react";
import { apiRequest } from "../services/api";

function DynamicForm({  endpoint,objectKeys, onSuccess }) {
  const [formData, setFormData] = useState({});

  // ====== Change ======
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  // ====== Submit ======
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await apiRequest(endpoint, "POST", formData);
      console.log("Form submitted:", formData);
      onSuccess?.();
      alert("Creado correctamente");
    } catch (err) {
      alert("Error al crear");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h3>Formulario dinámico</h3>

      {objectKeys.map((key) => (
        <div key={key} style={{ marginBottom: "8px" }}>
          <label>{key}</label>
          <input
            name={key}
            value={formData[key] || ""}
            onChange={handleChange}
          />
        </div>
      ))}

      <button type="submit">Guardar</button>
    </form>
  );
}

export default DynamicForm;
