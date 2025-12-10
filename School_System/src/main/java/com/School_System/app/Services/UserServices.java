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
import com.School_System.app.DTO.RecoverPassword;
import com.School_System.app.DTO.UserDTO;
import com.School_System.app.Model.Profesor;
import com.School_System.app.Model.User;
import com.School_System.app.Repository.UserRepository;
import java.security.SecureRandom;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author yesec
 */
@Service
@RequiredArgsConstructor
public class UserServices extends Crud<User, Long, UserDTO, UserDTO> {

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public void createUser(Profesor profesor, String rol) {
        // 3. Crear usuario vinculado
        User user = new User();
        user.setUsuario(profesor.getCorreoInstitucional());
        String encrypted = passwordEncoder.encode(profesor.getNumeroIdentificacion());
        user.setContrasena(encrypted);
        user.setRol(rol);
        user.setEstado(true);
        user.setProfesor(profesor);

        userRepository.save(user);
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

        boolean ismactchpassword = passwordEncoder.matches(request.getContrasena(),user.getContrasena());
        //if (!user.getContrasena().equals(request.getContrasena())) {
        if(!ismactchpassword){
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

    public UserDTO recoverPassword(RecoverPassword request) {
        Optional<User> userOpt = userRepository.findByUsuario(request.getUsuario());
      

        if (userOpt.isEmpty()) {
           throw new RuntimeException("Usuario no encontrado");
        }
         User user = userOpt.get();
        if (!Boolean.TRUE.equals(user.getEstado())) {
             throw new RuntimeException("Usuario inactivo");
        }
        UserDTO dto = new UserDTO();
        dto.fromEntity(user);

        return dto;
    }

    public String functionValUser(Optional<User> userOpt) {

        return null;
    }
    /* public void RecuperacionPassword(LoginDTO request) {
        User user = userRepository.findByUsuario(request.getUsuario())
            .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        // 1. Generar un código aleatorio (ej. 6 dígitos)
        String codigo = generarCodigoSeisDigitos();
        
        // 2. Guardar el código y su expiración (ej. 15 minutos)
        user.setCodRecuperacion(codigo);
        user.setCodRecuExpiracion(LocalDateTime.now().plusMinutes(15));
        userRepository.save(user);

        // 3. SIMULAR EL ENVÍO: Imprimir en consola en lugar de enviar SMS real
        System.out.println("--------------------------------------------------");
        System.out.println("SIMULACION SMS: El código para " + request.getUsuario() + " es: " + codigo);
        System.out.println("--------------------------------------------------");

        // Cuando pases a producción, aquí es donde integrarías Twilio
        // smsService.enviarSmsRestablecimiento(user.getTelefono(), codigo);
    }
    
    // Método auxiliar para generar un código simple
    private String generarCodigoSeisDigitos() {
        SecureRandom random = new SecureRandom();
        int num = 100000 + random.nextInt(900000);
        return String.valueOf(num);
    }
     */

}
