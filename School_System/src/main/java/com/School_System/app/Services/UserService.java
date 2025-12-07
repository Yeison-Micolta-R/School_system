/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.School_System.app.Services;

import com.School_System.app.DTO.*;
import java.util.List;

/**
 *
 * @author yesec
 */
public interface UserService {
    UserResponse crearUser(UserRequest request);
    List<UserResponse> listarUser();
    UserResponse obtenerUser(Long id);
    UserResponse actualizarUser(Long id, UserRequest request);
    //void desactivarProfesor(Long id);
}
