/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author yesec
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class PersonaBase {

    @Column(nullable = false, length = 150)
    private String nombreCompleto;

    @Column(nullable = false, length = 150)
    private String apellidosCompletos;

    @Column(nullable = false, length = 20)
    private String tipoDocumentoIdentidad;

    @Column(nullable = false, unique = true, length = 50)
    private String nDocIden;

    @Column(nullable = false)
    private Date fechaNacimiento;

    @Column(nullable = false, length = 20)
    private String generoSexo;

    @Column(nullable = false, length = 50)
    private String nacionalidad;

    @Column(nullable = false, length = 200)
    private String direccionResidencia;

    @Column(nullable = false, length = 100)
    private String ciudadResidencia;

    @Column(nullable = false, length = 100)
    private String correoInstitucional;

    @Column(nullable = false, length = 20)
    private String telefonoCelular;

    @Column(length = 20)
    private String telefonoSecundario;

    @Column(nullable = false, length = 100)
    private String contactoEmergenciaNombre;

    @Column(nullable = false, length = 50)
    private String contactoEmergenciaParentesco;

    @Column(nullable = false, length = 20)
    private String contactoEmergenciaTelefono;

    @Column(length = 300)
    private String condicionesMedicasRelevantes;

    @Column(length = 5)
    private String tipoSangre;

   /* @Column(nullable = false, length = 30)
    private String rolInstitucional;
*/
    @Builder.Default
    private Boolean estado = true;

}

