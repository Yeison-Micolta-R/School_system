/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.DTO;

/**
 *
 * @author yesec
 */
import lombok.*;
import jakarta.validation.constraints.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfesorRequest {
     @NotBlank
    private String numeroIdentificacion;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    private String telefono;

    @Email
    private String correoInstitucional;
}
