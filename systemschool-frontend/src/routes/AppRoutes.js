import { Routes, Route, Navigate } from "react-router-dom";
import Login from "../pages/Login";
import AdminDashboard from "../pages/admin/AdminDashboard";   
import GestionActores from "../pages/admin/GestionActores";
import Users from "../pages/admin/Users";
import StudentDashboard from "../pages/Student/StudentDashboard";
import Studet from "../pages/Student/Student";
import TeacherDashboard from "../pages/Teacher/TeacherDashboard"; 
import PerfilTeacher from "../pages/Teacher/PerfilTeacher";
import MainLayout from "../components/Layouts/MainLayout";
import Teacher from "../pages/admin/Teacher";

function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/login" />} />
      <Route path="/login" element={<Login />} />

      {/* Rutas del administrador */}
      <Route path="/admin" element={<MainLayout />}>
        <Route index element={<AdminDashboard />} />
        <Route path="GestionActores" element={<GestionActores />} />
        <Route path="Users" element={<Users />} />
        <Route path="Teacher" element={<Teacher />} />
      </Route>

      {/* Rutas del estudiante */}
      <Route path="/student" element={<MainLayout />}>
        <Route index element={<StudentDashboard />} />
        <Route path="Student" element={<Studet />} />
      </Route>

      {/* Rutas del profesor */}
      <Route path="/Teacher" element={<MainLayout />}>
        <Route index element={<TeacherDashboard />} />
        <Route path="PerfilTeacher" element={<PerfilTeacher />} />
      </Route>
    </Routes>
  );
}

export default AppRoutes;
