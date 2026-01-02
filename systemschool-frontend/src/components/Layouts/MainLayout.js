import { Outlet } from "react-router-dom";
import Sidebar from "../Sidebar";
import Header from "../Header";
import Footer from "../Footer";

function MainLayout() {
  return (
    <div className="dashboard-container">
      <Header />
      <div className="content-wrapper" style={{ display: "flex" }}>
        <Sidebar /> 
        <main className="main-content" style={{ flexGrow: 1, padding: "20px" }}>
          {/* Outlet renderiza el componente de la ruta hija actual */}
          <Outlet />
        </main>
      </div>
      <Footer />
    </div>
  );
}

export default MainLayout;
