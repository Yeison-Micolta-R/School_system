/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.controller;

/**
 *
 * @author yesec
 */



import com.School_System.app.DTO.UserDTO;
import jakarta.servlet.http.HttpSession;

public abstract class BaseController {

    protected UserDTO requireSession(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("Sesión expirada o no iniciada");
        }
        return user;
    }

    protected void requireRole(HttpSession session, String... rolesPermitidos) {
        UserDTO user = requireSession(session);

        for (String rol : rolesPermitidos) {
          // System.out.println("roles para esta accion -> "+ rol + " user-> " + user.getRol());
            if (user.getRol().equals(rol)) {
                return;
            }
        }

        throw new RuntimeException("No tienes permisos para esta acción");
    }
}

