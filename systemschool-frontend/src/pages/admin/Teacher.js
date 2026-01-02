import { useState, useEffect } from "react";
import { getColumns, getRowValues, formatValue } from "../../utils/tableUtils";
import { apiGet } from "../../services/api";
import { Dynamic,getEmptyUser } from "../../utils/renderDetail";
import { getDisplayedData } from "../../utils/crudHandlers";


function Teacher() {

  
  const [teachers, setTeachers] = useState([]);
  const [error, setError] = useState("");
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedTeacher, setSelectedTeacher] = useState(null);
  const [showTeacherForm, setShowTeacherForm] = useState(false);
  const fetchTeachers = async () => {
      try {
        const data = await apiGet("/profesor");
        setTeachers(data);
      } catch (err) {
        setError("No se pudo cargar la lista de docentes");
      }
    };
  useEffect(() => {
  
    fetchTeachers();
  }, []);

  const displayedTeachers = getDisplayedData(teachers, searchTerm, ["numeroIdentificacion", "nombre"]);

  return (
    <div>
      <h2>Gestión de Docentes</h2>
      
      <input
        type="text"
        placeholder="🔍 Buscar..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        style={{ padding: "8px", width: "300px", marginBottom: "10px" }}
      />

      {/* BOTÓN CREAR: Resetea el seleccionado y abre el form */}
      <button onClick={() => {
        setSelectedTeacher(null);
        setShowTeacherForm(true);
      }}>
        Crear Nuevo
      </button>

      {showTeacherForm && (
        <Dynamic
          data={
            selectedTeacher
              ? selectedTeacher // Tiene ID -> DynamicForm hará PUT (Actualizar)
              : getEmptyUser(teachers)
                
          }
          endpoint="/profesor"
          onSuccess={() => {
            setShowTeacherForm(false);
            fetchTeachers(); // Recargar la tabla tras éxito
            // Opcional: refrescar lista aquí
          }}
        />
      )}

      {error && <p style={{ color: "red" }}>{error}</p>}

      {displayedTeachers.length > 0 ? (
        <table border="1">
          <thead>
            <tr>
              {getColumns(teachers).map((key) => (
                <th key={key}>{key}</th>
              ))}
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {displayedTeachers.map((teacher) => (
              <tr key={teacher.id}>
                {getRowValues(teacher).map((value, i) => (
                  <td key={i}>{formatValue(value)}</td>
                ))}
                <td>
                  <button onClick={() => {
                    setSelectedTeacher(teacher); // 👈 GUARDA EL PROFESOR A EDITAR
                    setShowTeacherForm(true);    // 👈 ABRE EL MISMO FORMULARIO
                  }}>
                    Editar
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No se encontraron Docentes.</p>
      )}
    </div>
  );
}

export default Teacher;
