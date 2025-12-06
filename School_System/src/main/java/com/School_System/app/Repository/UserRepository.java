/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.School_System.app.Repository;

import com.School_System.app.Model.Profesor;
import com.School_System.app.Model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yesec
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsuario(String usuario);
    // boolean existsByUsuario(String ususario);
    List<User> findByRol(String rol);
    Page<User> findByEstadoTrue(Pageable paginacion);

}
