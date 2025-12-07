/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.controller;

import com.School_System.app.DTO.UserDTO;
import com.School_System.app.Model.User;
import com.School_System.app.Services.UserServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yesec
 */
@RestController
@RequestMapping("/user")
public class UserController extends Controller<User, Long, UserDTO, UserDTO> {
     public UserController(UserServices service) {
        super(service);
    }
}
