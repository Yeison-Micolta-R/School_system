/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.School_System.app.Services;

import com.School_System.app.DTO.ProfesorRequest;
import com.School_System.app.DTO.ProfesorResponse;
import java.util.List;


/**
 *
 * @author yesec
 */

public interface ServiceTeacher {
    ProfesorResponse crearProfesor(ProfesorRequest request);
    List<ProfesorResponse> listarProfesor();
    ProfesorResponse obtenerProfesor(Long id);
    ProfesorResponse actualizarProfesor(Long id, ProfesorRequest request);
    void desactivarProfesor(Long id);
}
