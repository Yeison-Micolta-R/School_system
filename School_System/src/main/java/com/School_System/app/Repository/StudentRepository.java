/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.School_System.app.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.School_System.app.Model.Estudiante;

/**
 *
 * @author yesec
 */
public interface StudentRepository extends JpaRepository<Estudiante, Long> {

    boolean existsByCorreoInstitucional(String correoInstitucional);

    Optional<Estudiante> findByNDocIden(String nDocIden);

    // Estudiantes activos
    List<Estudiante> findByEstadoTrue();

    Page<Estudiante> findByEstadoTrue(Pageable pageable);

    // Estudiantes por curso
    List<Estudiante> findByCursoId(Long cursoId);
  
}
