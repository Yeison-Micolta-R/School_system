import { useState, useEffect } from "react";
import { getColumns, getRowValues, formatValue } from "../../utils/tableUtils";
import { apiGet } from "../../services/api";
import { Dynamic,getEmptyUser } from "../../utils/renderDetail";
import { getDisplayedData } from "../../utils/crudHandlers";

function Student() {
  const [students, setStudents] = useState([]);
  const [error, setError] = useState("");
  const [searchTerm, setSearchTerm] = useState("");
  const [showStudentForm, setShowStudentForm] = useState(false);
  const [selectedStudent, setSelectedStudent] = useState(null);

  const displayedStudents = getDisplayedData(students, searchTerm, ["numeroDocumento", "nombreCompleto"]);
  
  // Función para refrescar datos
  const fetchStudents = async () => {
    try {
      const data = await apiGet("/estudiante");
      setStudents(data);
    } catch (err) {
      console.error(err);
      setError("No se pudo cargar la lista de estudiantes");
    }
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  // Función para preparar la creación (limpia el seleccionado)
  const handleCreateClick = () => {
    setSelectedStudent(null);
    setShowStudentForm(true);
  };

  // Función para preparar la edición
  const handleEditClick = (student) => {
    setSelectedStudent(student);
    setShowStudentForm(true);
  };


  return (
    <div>
      <h2>Gestión de Estudiantes</h2>
  

      {error && <p style={{ color: "red" }}>{error}</p>}

      <div style={{ marginBottom: "20px" }}>
        <input
          type="text"
          placeholder="🔍 Buscar por documento o nombre..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          style={{ padding: "8px", width: "300px", marginRight: "10px" }}
        />
        <button onClick={handleCreateClick}>Crear Nuevo Estudiante</button>
      </div>

      {showStudentForm && (
        <div style={{ border: "1px solid #ccc", padding: "15px", marginBottom: "20px" }}>
          
          <Dynamic
            data={selectedStudent || getEmptyUser(students)()}
            endpoint="/estudiante"
            onSuccess={() => {
              setShowStudentForm(false);
              fetchStudents(); // Recargar la tabla tras éxito
            }}
          />
          <button onClick={() => setShowStudentForm(false)}>Cancelar</button>
        </div>
      )}

      {displayedStudents.length > 0 ? (
        <table border="1" style={{ width: "100%", borderCollapse: "collapse" }}>
          <thead>
            <tr>
              {getColumns(students).map((key) => (
                <th key={key} style={{ padding: "10px", background: "#f4f4f4" }}>{key}</th>
              ))}
              <th style={{ padding: "10px", background: "#f4f4f4" }}>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {displayedStudents.map((student) => (
              <tr key={student.id}>
                {getRowValues(student).map((value, i) => (
                  <td key={i} style={{ padding: "8px" }}>{formatValue(value)}</td>
                ))}
                <td style={{ padding: "8px" }}>
                  <button onClick={() => handleEditClick(student)}>Editar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No se encontraron estudiantes.</p>
      )}
    </div>
  );
}

export default Student;
