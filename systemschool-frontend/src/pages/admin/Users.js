import { useEffect, useState } from "react";
import { apiRequest } from "../../services/api";


import { getColumns, formatValue, getFormFields } from "../../utils/tableUtils";
import { renderDetail, Dynamic, getEmptyUser } from "../../utils/renderDetail";
import { handleDelete, handleUpdate, handleCreate, getDisplayedData } from "../../utils/crudHandlers";

function Users() {
  // ================== ESTADOS ==================
  const [users, setUsers] = useState([]);
  const [error, setError] = useState("");
  const [selectedUser, setSelectedUser] = useState(null); // Para el modal de "Ver más"

  // Edición en línea (Tabla)
  const [editRowId, setEditRowId] = useState(null);
  const [formData, setFormData] = useState({});

  // Control de formularios dinámicos (Modales/Formularios externos)
  const [showTeacherForm, setShowTeacherForm] = useState(false);
  const [showStudentForm, setShowStudentForm] = useState(false);

  // DATOS para los formularios dinámicos (Lo que te faltaba)
  const [selectedStudent, setSelectedStudent] = useState(null);

  const [searchTerm, setSearchTerm] = useState("");

  const editableFields = ["usuario", "rol", "estado"];


  // ================== GET ==================
  useEffect(() => {
    apiRequest("/user", "GET")
      .then(setUsers)
      .catch(() => setError("No se pudieron cargar los usuarios"));
  }, []);

  // ================== DELETE ==================
  const deleteUser = (id) => {
    handleDelete({
      endpoint: "/user",
      id,
      setList: setUsers,
      selectedItem: selectedUser,
      setSelectedItem: setSelectedUser,
      setError,
      confirmMessage: "¿Eliminar usuario?",
    });
  };

  const deleteTeacher = (id) => {
    handleDelete({
      endpoint: "/profesor",
      id,
      setError,
      confirmMessage: "¿Eliminar docente?",
    });
  };

  const deleteStudent = (id) => {
    handleDelete({
      endpoint: "/estudiante",
      id,
      setError,
      confirmMessage: "¿Eliminar estudiante?",
    });
  };

  // ================== UPDATE ==================
  const handleEditClick = (user) => {
    setEditRowId(user.id);
    setFormData(user);
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSave = () => {
    handleUpdate({
      endpoint: "/user",
      id: editRowId,
      updatedData: formData,
      setList: setUsers,
      setSelectedItem: setSelectedUser,
      setError,
    });
    setEditRowId(null);
  };
  //listar usuarios con filtro de búsqueda
  //const displayedUsers = getDisplayedData(users, searchTerm);
  const displayedUsers = getDisplayedData(users, searchTerm, ["rol", "profesorId", "usuario", "teacher.nombre", "student.nombre"]);

  // ================== RENDER ==================
  return (
    <div>


      <h2>Gestión de Usuarios</h2>
      <div><h2></h2>
        <div><a href="/Student/Student">Estudiantes</a>
          <a href="/Teacher/Teacher">Docentes</a>
          <a >Acudientes</a>
          <a >Personal administrativo</a></div>
      </div>
      {/* Campo de búsqueda */}
      <div style={{ marginBottom: "20px" }}>
        <input
          type="text"
          placeholder="🔍 Buscar usuario, rol o datos de profesor..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)} // Actualiza el estado
          style={{ padding: "8px", width: "300px" }}
        />


        {/* Formulario para Profesor */}
        {showTeacherForm && selectedUser && (
          <Dynamic
            data={
              selectedUser.teacher
                ? selectedUser.teacher // Caso 1: Ya existe (Edita -> PUT)
                : (() => {
                  // Caso 2: No existe, creamos un molde limpio (Crea -> POST)
                  // Buscamos el primer usuario que tenga profesor para copiar la estructura
                  const example = users.find(u => u.teacher)?.teacher || {};
                  return Object.keys(example).reduce((acc, key) => {
                    if (key !== "id") acc[key] = ""; // ELIMINAMOS EL ID PARA FORZAR POST
                    return acc;
                  }, { usuarioId: selectedUser.id }); // Añadimos el vínculo al usuario
                })()
            }
            endpoint="/profesor"
            onSuccess={() => {
              setShowTeacherForm(false);
              window.location.reload();
            }}
          />
        )}

        {/* Formulario para Estudiante */}
        {showStudentForm && selectedUser && (
          <Dynamic
            data={
              selectedUser.student
                ? selectedUser.student
                : Object.keys(users.find(u => u.student)?.student || {}).reduce((acc, key) => {
                  if (key !== "id") acc[key] = "";
                  return acc;
                }, {})
            }
            endpoint="/estudiante"
            onSuccess={() => {
              setShowStudentForm(false);

            }}
          />
        )}

      </div>
      {/* ===== TABLA ===== */}
      {displayedUsers.length > 0 ? (
        /*{users.length > 0 && (*/
        <table border="1">
          <thead>
            <tr>
              {getColumns(users).map((key) => (

                <th key={key}>{key}</th>
              ))}
              <th>Más info</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {/*{displayedUsers.map((user) => (*/}
            {displayedUsers.map((user) => (
              <tr key={user.id}>
                {getColumns(users).map((col) => (
                  <td key={col}>
                    {editRowId === user.id &&
                      editableFields.includes(col) ? (
                      <input
                        name={col}
                        value={formData[col] ?? ""}
                        onChange={handleChange}
                      />
                    ) : (
                      formatValue(user[col])
                    )}
                  </td>
                ))}

                <td>
                  {(user.teacher || user.student) && (
                    <button onClick={() => setSelectedUser(user)}>
                      Ver
                    </button>
                  )}
                </td>

                <td>
                  {editRowId === user.id ? (
                    <>
                      <button onClick={handleSave}>Guardar</button>
                      <button onClick={() => setEditRowId(null)}>
                        Cancelar
                      </button>
                    </>
                  ) : (
                    <>
                      <button onClick={() => handleEditClick(user)}>
                        Editar
                      </button>
                      <button
                        onClick={() => deleteUser(user.id)}
                        style={{ color: "red" }}
                      >
                        Eliminar
                      </button>
                    </>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>

      ) : (<p>No se encontraron usuarios.</p>
      )}
      {/* ===== DETALLES ===== */}
      {selectedUser?.teacher &&

        renderDetail(
          selectedUser.teacher,
          "Maestro",
          () => setSelectedUser(null),
          () => setShowTeacherForm(true),
          () => deleteTeacher(selectedUser.teacher.id)
        )

      }




      {selectedUser?.student &&
        renderDetail(
          selectedUser.student, // Datos del detalle
          "Estudiante",         // Título
          () => setSelectedUser(null), // Cerrar
          () => {
            // Generamos el molde vacío basado en la lista que ya tenemos
            const nuevoEstudianteVacio = getEmptyUser(selectedUser.student);

            setSelectedStudent(nuevoEstudianteVacio);
            setShowStudentForm(true);
            setSelectedUser(null);
          },
          () => deleteStudent(selectedUser.student.id) // Acción de Eliminar
        )}


      {!selectedUser && <p>Seleccione un usuario para ver más detalles.</p>}
    </div>
  );
}


export default Users;
