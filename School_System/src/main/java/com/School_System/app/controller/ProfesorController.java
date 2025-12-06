/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.controller;

import com.School_System.app.DTO.ProfesorRequest;
import com.School_System.app.DTO.ProfesorResponse;
import com.School_System.app.Services.ServiceTeacher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author yesec
 */
@RestController
@RequestMapping("/profesor")
@RequiredArgsConstructor
public class ProfesorController {
      private final ServiceTeacher profesorService;

    // Crear profesor
    @PostMapping
    public ResponseEntity<ProfesorResponse> crearProfesor(@RequestBody ProfesorRequest request) {
        ProfesorResponse response = profesorService.crearProfesor(request);
        return ResponseEntity.ok(response);
    }

    // Listar profesores
    @GetMapping
    public ResponseEntity<List<ProfesorResponse>> listarProfesores() {
        List<ProfesorResponse> profesores = profesorService.listarProfesor();
        return ResponseEntity.ok(profesores);
    }

    // Obtener profesor por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorResponse> obtenerProfesor(@PathVariable Long id) {
        ProfesorResponse profesor = profesorService.obtenerProfesor(id);
        return ResponseEntity.ok(profesor);
    }

    // Actualizar profesor
    @PutMapping("/{id}")
    public ResponseEntity<ProfesorResponse> actualizarProfesor(
            @PathVariable Long id,
            @RequestBody ProfesorRequest request
    ) {
        ProfesorResponse actualizado = profesorService.actualizarProfesor(id, request);
        return ResponseEntity.ok(actualizado);
    }

    // Desactivar profesor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarProfesor(@PathVariable Long id) {
        profesorService.desactivarProfesor(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}
