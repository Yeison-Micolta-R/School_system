/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Services;

import com.School_System.app.DTO.TeacherDTO;
import com.School_System.app.DTO.UserDTO;

import com.School_System.app.Model.User;
import com.School_System.app.Repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yesec
 */
@Service
@RequiredArgsConstructor
public class UserServices extends Crud<User, Long, UserDTO, UserDTO>{
    private final UserRepository userRepository;
 
    @Override
    protected UserRepository getJpaRepository() {
        return userRepository;
    }

    @Override
    public UserDTO createResponse(User entity) {
        UserDTO dto = UserDTO.builder().build();
        dto.fromEntity(entity);
        return dto;
    }

    @Override
    public List<UserDTO> select() {
        return userRepository.findAllByEstadoTrue().stream()
                .map(this::createResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void desactivar(Long id) {
        User user = getJpaRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("User no encontrado"));
        user.setEstado(false);
        userRepository.save(user);
     
    }
}
