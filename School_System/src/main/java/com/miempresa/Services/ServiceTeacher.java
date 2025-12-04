/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.miempresa.Services;

import com.miempresa.DTO.ProfesorRequest;
import com.miempresa.DTO.ProfesorResponse;
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
