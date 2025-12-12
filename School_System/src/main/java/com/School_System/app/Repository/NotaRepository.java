/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.School_System.app.Repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.School_System.app.Model.Nota;

/**
 *
 * @author yesec
 */
public interface NotaRepository extends JpaRepository<Nota, Long> {

    // Notas por periodo
    List<Nota> findByPeriodo(String periodo);

    // Notas por estudiante
    List<Nota> findByEstudianteId(Long estudianteId);

    // Notas por asignatura
    List<Nota> findByAsignaturaId(Long asignaturaId);

    // Buscar nota única por estudiante, asignatura y periodo
    Optional<Nota> findByEstudianteIdAndAsignaturaIdAndPeriodo(
            Long estudianteId,
            Long asignaturaId,
            String periodo
    );
}
