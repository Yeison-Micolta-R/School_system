/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

/**
 *
 * @author yesec
 */
@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String usuario;

    @Column(nullable = false, length = 100)
    private String contrasena;

    @Column(length = 20)
    private String rol; // Puede ser ESTUDIANTE, PROFESOR o ADMIN

    @Builder.Default
    private Boolean estado = true;
    // Relación con Estudiante
    // @OneToOne
    // @JoinColumn(name = "id_estudiante",nullable = true)
    //private Estudiante estudiante;

    @OneToOne
    @JoinColumn(name = "id_teacher", nullable = true)
    private Profesor profesor;

    @OneToOne
    @JoinColumn(name = "id_student", nullable = true)
    private Estudiante estudiante;

    private String codRecuperacion;
    private LocalDateTime codRecuExpiracion;

    // Métodos auxiliares
    public void desactivar() {
        this.estado = false;
    }
}
