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
}

