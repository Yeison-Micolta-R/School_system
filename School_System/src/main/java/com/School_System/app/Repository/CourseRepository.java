/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.School_System.app.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.School_System.app.Model.Curso;

/**
 *
 * @author yesec
 */
public interface CourseRepository extends JpaRepository<Curso, Long> {

    boolean existsByNombre(String nombre);

    Optional<Curso> findByNombre(String nombre);

    // Buscar cursos por director
    List<Curso> findByDirectorId(Long profesorId);

    // Cursos por jornada
    List<Curso> findByJornada(String jornada);

    // Cursos por nivel académico
    List<Curso> findByNivelAcademicoId(Long nivelId);
}

