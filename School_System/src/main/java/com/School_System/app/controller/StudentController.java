/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.controller;

import com.School_System.app.DTO.EstudianteDTO;
import com.School_System.app.Model.Estudiante;
import com.School_System.app.Services.StudentService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author yesec
 */
public class StudentController extends Controller<Estudiante, Long, EstudianteDTO, EstudianteDTO> {
   public StudentController(StudentService service) {
        super(service);
    }
    @PostMapping
   @Override
    public EstudianteDTO create(@RequestBody EstudianteDTO request, HttpSession session) {
        requireRole(session, "Secretaria");
        return super.create(request, session);

    }

    @GetMapping
   @Override
    public List<EstudianteDTO> select(HttpSession session) {
        requireRole(session, "Secretaria","Profesor","Administrador");
      

        return super.select(session);
    }

    @GetMapping("/{id}")
   @Override
    public ResponseEntity<EstudianteDTO> buscar(@PathVariable Long id, HttpSession session) {
        requireRole(session, "Administrador","Secretaria","Profesor");
     
       
        return super.buscar(id, session);

    }

    @PutMapping("/{id}")
   @Override
    public ResponseEntity<EstudianteDTO> update(@PathVariable Long id, @RequestBody EstudianteDTO request, HttpSession session) {
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
