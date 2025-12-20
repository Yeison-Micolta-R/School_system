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
@Table(name = "nivel_academico")
@Data  
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NivelAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    
     @Builder.Default
    @OneToMany(mappedBy = "nivelAcademico")
    private Set<Curso> Curso = new HashSet<>();

   
}
