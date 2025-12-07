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
import org.springframework.stereotype.Repository;

import com.School_System.app.Model.Profesor;
import com.School_System.app.Model.User;

/**
 *
 * @author yesec
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
   Optional<User> findByUsuario(String usuario);
    List<User> findByRol(String rol);
    Optional<User> findByProfesor(Profesor profesor);
    Optional<User> findByProfesorId(Long id_teacher);
    Page<User> findByEstadoTrue(Pageable paginacion);
    List<User> findAllByEstadoTrue();


}
