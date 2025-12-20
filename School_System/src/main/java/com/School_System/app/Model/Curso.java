/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
    private Profesor Profesorid;
    
    
    // NIVEL ACADÉMICO
    @ManyToOne
    @JoinColumn(name = "nivel_academico", nullable = false)
    private NivelAcademico nivelAcademico;

    // Estudiantes del curso
     @Builder.Default
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Estudiante> estudiantes = new HashSet<>();

    // Profesores del curso (por asignatura)
      @Builder.Default
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Asignatura> asignaturas = new HashSet<>();
}
 



