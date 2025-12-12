/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.School_System.app.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.School_System.app.Model.Asignatura;

/**
 *
 * @author yesec
 */
public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {

    // Validar duplicado
    boolean existsByNombre(String nombre);

    // Buscar por nombre exacto
    Optional<Asignatura> findByNombre(String nombre);

    // Buscar asignaturas de un profesor
    List<Asignatura> findByProfesorId(Long profesorId);

    // Buscar asignaturas por curso
    List<Asignatura> findByCursoId(Long cursoId);
}
