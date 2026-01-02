import { Link } from "react-router-dom";

function Sidebar() {
  // Recuperar usuario desde localStorage
  const user = JSON.parse(localStorage.getItem("user"));
  const rol = user?.rol; // puede ser "Administrador", "Estudiante", "Docente", "Secretaria"

  return (
    <nav className="sidebar">
      <ul>
        <p>Bienvenido: {user?.usuario}</p>
        <p>Tu rol es: {user?.rol}</p>
        <p>ID de usuario: {user?.id}</p>
      </ul>
      {rol === "Administrador" && (
        <ul className="sidebar-admin">
          <li><Link to="/admin">inicio</Link></li>

          <li>Núcleo de la Institución Educativa</li>

          <li><Link to="/admin/Users">Actores</Link></li>
          <li>Gestión Pedagógica</li>
          <li>Convivencia y Bienestar</li>
          <li>Gestión Administrativa</li>
          <li>Comunicación y Gobierno Escolar</li>
          <li>Panel de Indicadores</li>
        </ul>
      )}
      {rol === "Secretaria" && (
        <ul className="sidebar-secretaria">
          <li><Link to="/secretaria">Dashboard</Link></li>
          <li><Link to="/secretaria/enrollment">Matrículas</Link></li>
          <li><Link to="/secretaria/docs">Documentos</Link></li>
        </ul>
      )}

      {rol === "Estudiante" && (
        <ul className="sidebar-student">
          <li><Link to="/student">Dashboard</Link></li>
          <li><Link to="/student/courses">Mis Cursos</Link></li>
          <li><Link to="/student/grades">Mis Notas</Link></li>
          <li><Link to="/student/profile">Perfil</Link></li>
        </ul>
      )}
      {rol === "Profesor" && (
        <ul className="sidebar-teacher">
          <li><Link to="/teacher/dashboard">📊 Dashboard</Link></li>
          <li><Link to="/teacher/attendance">📝 Pasar Asistencia</Link></li>
          <li><Link to="/teacher/grades">⭐ Calificaciones</Link></li>
          <li><Link to="/teacher/planner">📅 Planeador de Clases</Link></li>
          <li><Link to="/teacher/resources">📂 Material de Apoyo</Link></li>
          <li><Link to="/teacher/behavior">👁️ Observador</Link></li>
          <li><Link to="/teacher/messages">💬 Mensajería</Link></li>
          <li><Link to="/teacher/PerfilTeacher">👤 Mi Perfil</Link></li>
        </ul>
      )}



    </nav>
  );
}

export default Sidebar;