/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.controller;

import com.School_System.app.DTO.*;
import com.School_System.app.Services.Crud;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author yesec
 */

public abstract class Controller<T, ID, Req extends Request<T>, Res extends Response<T>> extends BaseController {
    protected Crud<T, ID, Req, Res> service;

    public Controller(Crud<T, ID, Req, Res> service){
        this.service = service;
    }

    @PostMapping
    public Res create(@RequestBody Req request, HttpSession session) {
        requireSession(session);
        System.out.println("request-> "+ request);
        T entity = service.create(request);
        
        return  service.createResponse(entity);  
        
    }

    @GetMapping
    public List<Res> select( HttpSession session) {
          requireSession(session);
        return service.select();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Res> buscar(@PathVariable ID id, HttpSession session) {
        requireSession(session);
        try {
            Res dto = service.buscar(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Res> update(@PathVariable ID id, @RequestBody Req request, HttpSession session) {
          requireSession(session);
          
        try {
            T updated = service.update(id, request);
            return ResponseEntity.ok(service.createResponse(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable ID id, HttpSession session) {
        requireSession(session);
        try {
            service.desactivar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
