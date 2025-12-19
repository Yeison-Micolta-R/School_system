/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.controller;

import com.School_System.app.DTO.AsignaturaDTO;
import com.School_System.app.Model.Asignatura;
import com.School_System.app.Services.AsignaturaService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yesec
 */
@RestController
@RequestMapping("/asignatura")
public class AsignaturaController extends Controller<Asignatura, Long, AsignaturaDTO, AsignaturaDTO> {
   public AsignaturaController(AsignaturaService service) {
        super(service);
    }  
     @PostMapping
    @Override
    public AsignaturaDTO create(@RequestBody AsignaturaDTO request, HttpSession session) {
        requireRole(session, "Secretaria");
        return super.create(request, session);

    }

    @GetMapping
    @Override
    public List<AsignaturaDTO> select(HttpSession session) {
        requireRole(session, "Secretaria","Profesor","Estudiante","Administrador");
      

        return super.select(session);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<AsignaturaDTO> buscar(@PathVariable Long id, HttpSession session) {
        requireRole(session, "Secretaria","Profesor","Estudiante","Administrador");
     
       // requireRole(session, "Estudiante");
        return super.buscar(id, session);

    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<AsignaturaDTO> update(@PathVariable Long id, @RequestBody AsignaturaDTO request, HttpSession session) {
        requireRole(session, "Secretaria");      
        return super.update(id, request, session);

    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> desactivar(@PathVariable Long id, HttpSession session) {
        requireRole(session, "Secretaria");
        return super.desactivar(id, session);

    }
}
