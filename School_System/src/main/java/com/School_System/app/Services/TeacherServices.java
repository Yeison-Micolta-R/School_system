/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Services;



import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.internal.CoreLogging.logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.School_System.app.DTO.TeacherDTO;
import com.School_System.app.Model.Profesor;
import com.School_System.app.Model.User;
import com.School_System.app.Repository.ProfesorRepository;
import com.School_System.app.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServices extends Crud<Profesor, Long, TeacherDTO, TeacherDTO> {

    private final ProfesorRepository profesorRepository;
    private final UserRepository userRepository;

    @Override
    protected ProfesorRepository getJpaRepository() {
        return profesorRepository;
    }

    @Override
    public TeacherDTO createResponse(Profesor entity) {        
        TeacherDTO dto = TeacherDTO.builder().build();

        dto.fromEntity(entity);
        return dto;
    }

    @Transactional
    @Override
    public void desactivar(Long id) {
        Profesor profesor = getJpaRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        profesor.setEstado(false);
        profesorRepository.save(profesor);

        // Desactivar usuario vinculado
        userRepository.findByProfesor(profesor).ifPresent(user -> {
            user.setEstado(false);
            userRepository.save(user);
        });
    }

    @Transactional
    public TeacherDTO crearProfesor(TeacherDTO request) {
    Profesor profesor = create(request);

    // 🔹 Depuración: imprimir todos los campos del profesor
    System.out.println("=== PROFESOR CREADO ===");
    System.out.println("ID: " + profesor.getId());
    System.out.println("Número de Identificación: " + profesor.getNumeroIdentificacion());
    System.out.println("Nombre: " + profesor.getNombre());
    System.out.println("Apellido: " + profesor.getApellido());
    System.out.println("Teléfono: " + profesor.getTelefono());
    System.out.println("Correo Institucional: " + profesor.getCorreoInstitucional());
    System.out.println("Estado: " + profesor.getEstado());
    System.out.println("=======================");
    logger.info("Profesor recibido: {}", request);
    // Vincular usuario
    User user = new User();
    user.setUsuario(profesor.getCorreoInstitucional());
    user.setContrasena(profesor.getNumeroIdentificacion());
    user.setRol("Profesor");
    user.setEstado(true);
    user.setProfesor(profesor);
    logger.info("user recibido: {}", request);
    // 🔹 Depuración: imprimir todos los campos del usuario antes de guardar
    System.out.println("=== USUARIO A GUARDAR ===");
    System.out.println("Usuario: " + user.getUsuario());
    System.out.println("Contraseña: " + user.getContrasena());
    System.out.println("Rol: " + user.getRol());
    System.out.println("Estado: " + user.getEstado());
    System.out.println("Profesor asociado: " + user.getProfesor());
    System.out.println("=========================");

    try {
        userRepository.save(user);
    } catch (Exception e) {
        throw new RuntimeException("Error al crear el usuario del profesor: " + e.getMessage(), e);
    }

    return createResponse(profesor);
}

    @Override
     public List<TeacherDTO> select() {
        return profesorRepository.findAllByEstadoTrue().stream()
                .map(this::createResponse)
                .collect(Collectors.toList());
     }
}

