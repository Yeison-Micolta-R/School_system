/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.controller;

import com.School_System.app.DTO.CursoDTO;
import com.School_System.app.Model.Curso;
import com.School_System.app.Services.CourseService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author yesec
 */
public class CourseController extends Controller<Curso, Long, CursoDTO, CursoDTO> {
   public CourseController(CourseService service) {
        super(service);
    }
   
     @PostMapping
    @Override
    public CursoDTO create(@RequestBody CursoDTO request, HttpSession session) {
        requireRole(session, "Secretaria");
        return super.create(request, session);

    }

    @GetMapping
    @Override
    public List<CursoDTO> select(HttpSession session) {
        requireRole(session, "Secretaria","Profesor","Administrador","Estudiante");
      

        return super.select(session);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CursoDTO> buscar(@PathVariable Long id, HttpSession session) {
        requireRole(session, "Secretaria","Profesor","Administrador","Estudiante");
     
       // requireRole(session, "Estudiante");
        return super.buscar(id, session);

    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<CursoDTO> update(@PathVariable Long id, @RequestBody CursoDTO request, HttpSession session) {
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
