/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author yesec
 */
@Entity
@Table(name = "estudiante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Estudiante extends PersonaBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Información extra de estudiantes
    @Column(nullable = false)
    private Date fechaIngreso;

    @Column(nullable = false)
    private Date fechaMatricula;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private User usuario;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
     @Builder.Default
    private Set<Nota> notas = new HashSet<>();

}
