/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.DTO;

import jakarta.validation.constraints.*;

/**
 *
 * @author yesec
 */
public class UserRequest {
    @NotBlank
    private String id;
    
    @NotBlank
    private String usuario;

    @NotBlank
    private String rol;

  

   
}
