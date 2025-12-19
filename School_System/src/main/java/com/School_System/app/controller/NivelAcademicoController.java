/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.controller;

import com.School_System.app.DTO.NivelAcademicoDTO;
import com.School_System.app.Model.NivelAcademico;
import com.School_System.app.Services.NivelAcademicoService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author yesec
 */
//@PreAuthorize("hasRole('Administrador')")
public class NivelAcademicoController extends Controller<NivelAcademico, Long, NivelAcademicoDTO, NivelAcademicoDTO> {
   public NivelAcademicoController(NivelAcademicoService service) {
        super(service);
    }
   @PostMapping
    @Override
    public NivelAcademicoDTO create(@RequestBody NivelAcademicoDTO request, HttpSession session) {
        requireRole(session, "Administrador");
        return super.create(request, session);

    }

    @GetMapping
    @Override
    public List<NivelAcademicoDTO> select(HttpSession session) {
        requireRole(session, "Administrador","Profesor","Estudiante","Secretaria");
      

        return super.select(session);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<NivelAcademicoDTO> buscar(@PathVariable Long id, HttpSession session) {
        requireRole(session, "Administrador","Profesor","Estudiante","Secretaria");
     
       // requireRole(session, "Estudiante");
        return super.buscar(id, session);

    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<NivelAcademicoDTO> update(@PathVariable Long id, @RequestBody NivelAcademicoDTO request, HttpSession session) {
        requireRole(session, "Administrador");      
        return super.update(id, request, session);

    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> desactivar(@PathVariable Long id, HttpSession session) {
        requireRole(session, "Administrador");
        return super.desactivar(id, session);

    }
    
}
