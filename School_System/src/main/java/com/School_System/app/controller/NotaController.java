/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.controller;

import com.School_System.app.DTO.NotaDTO;
import com.School_System.app.Model.Nota;
import com.School_System.app.Services.NotaService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author yesec
 */
public class NotaController extends Controller<Nota, Long, NotaDTO, NotaDTO> {
   public NotaController(NotaService service) {
        super(service);
    }
    @PostMapping
   @Override
    public NotaDTO create(@RequestBody NotaDTO request, HttpSession session) {
        requireRole(session, "Profesor");
        return super.create(request, session);

    }

    @GetMapping
   @Override
    public List<NotaDTO> select(HttpSession session) {
        requireRole(session,"Profesor");
      

        return super.select(session);
    }

    @GetMapping("/{id}")
   @Override
    public ResponseEntity<NotaDTO> buscar(@PathVariable Long id, HttpSession session) {
        requireRole(session,"Secretaria","Profesor","Estudiante");
     

        return super.buscar(id, session);

    }

    @PutMapping("/{id}")
   @Override
    public ResponseEntity<NotaDTO> update(@PathVariable Long id, @RequestBody NotaDTO request, HttpSession session) {
        requireRole(session, "Profesor");      
        return super.update(id, request, session);

    }

    @DeleteMapping("/{id}")
   @Override
    public ResponseEntity<Void> desactivar(@PathVariable Long id, HttpSession session) {
        requireRole(session, "Profesor");
        return super.desactivar(id, session);

    }
    
}
