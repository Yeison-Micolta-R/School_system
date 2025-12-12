/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Repository;
/**
 *
 * @author yesec
 */
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.School_System.app.Model.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Optional<Profesor> findByNumeroIdentificacion(String numeroIdentificacion);
    boolean existsByCorreoInstitucional(String correoInstitucional);
   // 
    List<Profesor> findAllByEstadoTrue();
    Page<Profesor> findByEstadoTrue(Pageable paginacion);
}