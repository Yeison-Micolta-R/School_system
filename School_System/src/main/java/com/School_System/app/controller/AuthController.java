/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.controller;

import com.School_System.app.DTO.*;

import com.School_System.app.Services.UserServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author yesec
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO request, HttpSession session) {
        try {
            UserDTO user = userServices.login(request);
            session.setAttribute("user", user);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            // System.out.println("usuario no encontrado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
    }
     @PostMapping("/recoverPassword")
    public ResponseEntity<String> recuperar(@RequestBody RecoverPassword request) {
        // Aquí solo recibes 'usuario' y la lógica es clara.
         UserDTO user = userServices.recoverPassword(request);
        // ... lógica para enviar correo ...
        return ResponseEntity.ok("Instrucciones enviadas al correo asociado a " + user.getUsuario());
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getProfile(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
        return ResponseEntity.ok(user);
    }   

}
