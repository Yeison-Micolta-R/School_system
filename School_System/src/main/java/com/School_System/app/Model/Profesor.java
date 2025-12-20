/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Model;

/**
 *
 * @author yesec
 */
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "profesor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profesor {    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroIdentificacion;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private String telefono;

    @Column(unique = true, nullable = false)
    private String correoInstitucional;

    @Builder.Default
    private Boolean estado = true;

    @OneToOne(mappedBy = "profesor", cascade = CascadeType.ALL)
    private User usuario;

    // Si es director de curso (ONE TO ONE)
    @OneToOne(mappedBy = "Profesorid")
    private Curso cursoDirigido;

    // Profesores que dictan clases (ManyToMany via Asignatura)
     @Builder.Default
    @OneToMany(mappedBy = "profesor")
    private Set<Asignatura> asignaturas = new HashSet<>();
}

