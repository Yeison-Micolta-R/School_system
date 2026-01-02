import MainLayout from "../../components/Layouts/MainLayout";
import { useState } from "react";
import { renderDetail, Dynamic, getEmptyUser,logout } from "../../utils/renderDetail";
import { handleDelete } from "../../utils/crudHandlers";
//import  {logout}  from "../../utils/Auth";
function PerfilTeacher() {
    // 1. Obtener datos del localStorage de forma segura
    const user = JSON.parse(localStorage.getItem("user"));
    const teacherData = user?.teacher;

    const [showTeacherForm, setShowTeacherForm] = useState(false);
    const [selectedTeacher, setSelectedTeacher] = useState(null);
    const [error, setError] = useState("");
const [showDetail, setShowDetail] = useState(true);

const deleteTeacher = async (id) => {
    try {
        // Ejecuta la lógica de eliminación
        const result = await handleDelete({
            endpoint: "/profesor",
            id,
            setError,
            confirmMessage: "¿Eliminar docente?",
        });
        console.log("Resultado de la eliminación:", result);
        // Si la función devuelve algo positivo al terminar
        if (result === false) {
           return console.log("no eliminado...");
           
        }

        logout();
    } catch (err) {
        console.error("Error al eliminar:", err);
    }
};

    return (

        <div style={{ padding: "20px" }}>
            <h1>Perfil del Profesor</h1>

            {showTeacherForm && (
                <Dynamic
                    data={selectedTeacher ? selectedTeacher : getEmptyUser(teacherData)}
                    endpoint="/profesor"
                    onSuccess={() => {
                        setShowTeacherForm(false);

                    }}
                />
            )}






            {teacherData  && showDetail ? (
                <div>
                    {renderDetail(
                        teacherData,
                        " del Profesor",
                        () => setShowDetail(false), 
                        () => {
                            setSelectedTeacher(teacherData);
                            setShowTeacherForm(true);
                        }, 
                        () => {
                             deleteTeacher(teacherData.id);

                        } 
                    )}
                </div>
            ) : (
                <p>No se encontraron datos del profesor.</p>
            )}
        </div>

    );
}

export default PerfilTeacher;
