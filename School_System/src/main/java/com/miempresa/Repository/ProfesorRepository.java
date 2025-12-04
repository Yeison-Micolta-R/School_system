/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.Repository;
/**
 *
 * @author yesec
 */
import com.miempresa.Model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Optional<Profesor> findByNumeroIdentificacion(String numeroIdentificacion);
    boolean existsByCorreoInstitucional(String correoInstitucional);
    Page<Profesor> findByActivoTrue(Pageable paginacion);
}