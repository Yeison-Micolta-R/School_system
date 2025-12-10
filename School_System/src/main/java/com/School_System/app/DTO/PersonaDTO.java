/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.DTO;

/**
 *
 * @author yesec
 */

import java.time.LocalDate; 
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class PersonaDTO<T> implements Request<T>, Response<T> {

    private String nombreCompleto;
    private String apellidosCompletos;
    private String tipoDocumentoIdentidad;
    private String numeroDocumentoIdentidad;
    private LocalDate fechaNacimiento;
    private String generoSexo;
    private String nacionalidad;
    private String direccionResidencia;
    private String ciudadResidencia;
    private String correoElectronico;
    private String telefonoCelular;
    private String telefonoSecundario;
    private String contactoEmergenciaNombre;
    private String contactoEmergenciaParentesco;
    private String contactoEmergenciaTelefono;
    private String condicionesMedicasRelevantes;
    private String tipoSangre;
    private String rolInstitucional;
    private String estadoActual;

  }
