/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Services;

import com.School_System.app.DTO.ProfesorRequest;
import com.School_System.app.DTO.ProfesorResponse;
import com.School_System.app.Model.Profesor;
import com.School_System.app.Model.User;
import com.School_System.app.Repository.ProfesorRepository;
import com.School_System.app.Repository.UserRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author yesec
 */
@Service
@RequiredArgsConstructor
public class CrudProfesor implements ServiceTeacher {
    
    private final ProfesorRepository profesorRepository;
    private final UserRepository userRepository;

    @Override
    public ProfesorResponse crearProfesor(ProfesorRequest request) {
        Profesor profesor = Profesor.builder()
                .numeroIdentificacion(request.getNumeroIdentificacion())
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .telefono(request.getTelefono())
                .correoInstitucional(request.getCorreoInstitucional())
                .estado(true)
                .build();
       
        Profesor guardado = profesorRepository.save(profesor);
        VincularUsuario(guardado, "Profesor", profesor.getNumeroIdentificacion(), profesor.getCorreoInstitucional());
       
      
     return mapToResponse(guardado);
      
    }

   
    @Override
    public List<ProfesorResponse> listarProfesor() {
        return profesorRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProfesorResponse obtenerProfesor(Long id) {
        return profesorRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
    }

    @Override
    public ProfesorResponse actualizarProfesor(Long id, ProfesorRequest request) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        profesor.setNombre(request.getNombre());
        profesor.setApellido(request.getApellido());
        profesor.setTelefono(request.getTelefono());
        profesor.setCorreoInstitucional(request.getCorreoInstitucional());

        Profesor actualizado = profesorRepository.save(profesor);
        return mapToResponse(actualizado);
    }

    @Override 
    public void desactivarProfesor(Long id) {
        Profesor profesor = profesorRepository.findById(id)
        
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        profesor.desactivar();
        user.desactivar();
        
        profesorRepository.save(profesor);
    }
    
    public User VincularUsuario(Profesor profesor,String rol, String contrasena,String user){
        User usuario = new User();
               usuario.setUsuario(user);
               usuario.setContrasena(contrasena);
               usuario.setRol(rol);
               usuario.setEstado(Boolean.TRUE);
               usuario.setProfesor(profesor);
               
           userRepository.save(usuario);
        return usuario;
        
    }
   
    private ProfesorResponse mapToResponse(Profesor profesor) {
        return ProfesorResponse.builder()
                .id(profesor.getId())
                .numeroIdentificacion(profesor.getNumeroIdentificacion())
                .nombre(profesor.getNombre())
                .apellido(profesor.getApellido())
                .telefono(profesor.getTelefono())
                .correoInstitucional(profesor.getCorreoInstitucional())
                .activo(profesor.getEstado())
                .build();
    }


}
