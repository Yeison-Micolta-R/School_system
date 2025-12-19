/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.School_System.app.DTO.TeacherDTO;
import com.School_System.app.Model.Profesor;
import com.School_System.app.Services.TeacherServices;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author yesec
 */
@RestController
@RequestMapping("/profesor")
public class TeacherController extends Controller<Profesor, Long, TeacherDTO, TeacherDTO> {

    public TeacherController(TeacherServices service) {
        super(service);
    }

    @PostMapping
    @Override
    public TeacherDTO create(@RequestBody TeacherDTO request, HttpSession session) {
        requireRole(session, "Secretaria");
        return super.create(request, session);

    }

    @GetMapping
    @Override
    public List<TeacherDTO> select(HttpSession session) {
        requireRole(session, "Secretaria","Profesor","Administrador");
      

        return super.select(session);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<TeacherDTO> buscar(@PathVariable Long id, HttpSession session) {
        requireRole(session, "Administrador","Secretaria");
     
       // requireRole(session, "Estudiante");
        return super.buscar(id, session);

    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<TeacherDTO> update(@PathVariable Long id, @RequestBody TeacherDTO request, HttpSession session) {
        requireRole(session, "Secretaria","Profesor");      
        return super.update(id, request, session);

    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> desactivar(@PathVariable Long id, HttpSession session) {
        requireRole(session, "Secretaria");
        return super.desactivar(id, session);

    }
}
