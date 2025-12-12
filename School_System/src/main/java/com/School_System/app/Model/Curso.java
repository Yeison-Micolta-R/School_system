/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;


/**
 *
 * @author yesec
 */
@Entity
@Table(name = "curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(length = 15)
    private String jornada;

    // DIRECTOR DEL CURSO (solo uno)
    @OneToOne
    @JoinColumn(name = "director_grado", nullable = false)
    private Profesor Director_curso;

    @OneToOne
    @JoinColumn(name = "nivel_academico", nullable = false)
    private NivelAcademico nivelAcademico;

    // Estudiantes del curso
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Estudiante> estudiantes = new HashSet<>();

    // Profesores del curso (por asignatura)
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Asignatura> asignaturas = new HashSet<>();
}
 



