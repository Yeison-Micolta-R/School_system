/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Services;

import com.School_System.app.DTO.EstudianteDTO;
import com.School_System.app.Model.Estudiante;
import com.School_System.app.Repository.StudentRepository;
import com.School_System.app.Repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yesec
 */
@Service
@RequiredArgsConstructor
public class StudentService extends Crud<Estudiante, Long, EstudianteDTO, EstudianteDTO> {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final UserServices user;

    @Transactional
    @Override
    public Estudiante create(EstudianteDTO request) {

        // 1. Convertir DTO a entidad
        Estudiante estudiante = request.toEntity();

        // 2. Guardar profesor
        estudiante = studentRepository.save(estudiante);

        user.createUser(null,estudiante,"Estudiante");
        return estudiante;
    }

   @Override
    protected StudentRepository getJpaRepository() {
        return studentRepository;
    }

    @Override
    public EstudianteDTO createResponse(Estudiante entity) {
        EstudianteDTO dto = EstudianteDTO.builder().build();
        dto.fromEntity(entity);
        return dto;
    }

    @Override
    public List<EstudianteDTO> select() {
        return studentRepository.findByEstadoTrue().stream()
                .map(this::createResponse)
                .collect(Collectors.toList());
    }
   /*public Profesor update(Long id,  TeacherDTO request) {
       actualizar cambos usuario
    }*/
    @Transactional
    @Override
    public void desactivar(Long id) {
        Estudiante student = getJpaRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        student.setEstado(false);
        studentRepository.save(student); // Desactivar usuario vinculado 
        userRepository.findByEstudiante(student).ifPresent(user -> {
            user.setEstado(false);
            userRepository.save(user);
        });
    }
}
