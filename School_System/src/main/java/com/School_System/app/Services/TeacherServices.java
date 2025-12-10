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
import com.School_System.app.Services.UserServices;
import com.School_System.app.Repository.ProfesorRepository;
import com.School_System.app.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServices extends Crud<Profesor, Long, TeacherDTO, TeacherDTO> {

    private final ProfesorRepository profesorRepository;
    private final UserRepository userRepository;
     private final UserServices user;
    @Transactional
    @Override
    public Profesor create(TeacherDTO request) {

        // 1. Convertir DTO a entidad
        Profesor profesor = request.toEntity();

        // 2. Guardar profesor
        profesor = profesorRepository.save(profesor);

        /*// 3. Crear usuario vinculado
        User user = new User();
        user.setUsuario(profesor.getCorreoInstitucional());
        user.setContrasena(profesor.getNumeroIdentificacion());
        user.setRol("Profesor");
        user.setEstado(true);
        user.setProfesor(profesor);

        userRepository.save(user);*/
       user.createUser(profesor, "Profesor");
        return profesor;
    }

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

    @Override
    public List<TeacherDTO> select() {
        return profesorRepository.findAllByEstadoTrue().stream()
                .map(this::createResponse)
                .collect(Collectors.toList());
    }
   /*public Profesor update(Long id,  TeacherDTO request) {
       actualizar cambos usuario
    }*/
    @Transactional
    @Override
    public void desactivar(Long id) {
        Profesor profesor = getJpaRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        profesor.setEstado(false);
        profesorRepository.save(profesor); // Desactivar usuario vinculado 
        userRepository.findByProfesor(profesor).ifPresent(user -> {
            user.setEstado(false);
            userRepository.save(user);
        });
    }

}
