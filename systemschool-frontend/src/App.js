/*import { useEffect, useState } from "react";

function App() {
  const [estudiantes, setEstudiantes] = useState([]);

  useEffect(() => {
  fetch("http://localhost:8080/estudiante", {
    credentials: "include"
  })
    .then(res => res.json())
    .then(data => {
      console.log("DATOS DEL BACKEND:", data);
      setEstudiantes(data);
    });
}, []);

  return (
    <div>
      <h1>SystemSchool</h1>

      <h2>Lista de Estudiantes</h2>

      <ul>
        {estudiantes.map(e => (
          <li key={e.id}>
            {e.ciudadResidencia} {e.apellidosCompletos}
          </li>
        ))}
      </ul>
    </div>
  );
}
*/import Login from "./Login";

function App() {
  return (
    <div>
      <h1>SystemSchool</h1>
      <Login />
    </div>
  );
}
export default App;

