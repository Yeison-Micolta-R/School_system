/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.School_System.app.DTO.LoginDTO;
import com.School_System.app.DTO.UserDTO;
import com.School_System.app.Model.User;
import com.School_System.app.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author yesec
 */
@Service
@RequiredArgsConstructor
public class UserServices extends Crud<User, Long, UserDTO, UserDTO> {

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

    public UserDTO login(LoginDTO request) {
         Optional<User> userOpt = userRepository.findByUsuario(request.getUsuario());

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        User user = userOpt.get();

        if (!user.getContrasena().equals(request.getContraseña())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        if (!Boolean.TRUE.equals(user.getEstado())) {
            throw new RuntimeException("Usuario inactivo");
        }

        // Convertimos a DTO
        UserDTO dto = new UserDTO();
        dto.fromEntity(user);

        return dto;
    }

}
