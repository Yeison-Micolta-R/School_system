/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.DTO;

import com.School_System.app.Model.Curso;
import lombok.Builder;

/**
 *
 * @author yesec
 */
@Builder
public class CursoDTO implements Request<Curso>, Response<Curso> {

    private Long id;

    private String nombre;

    private String jornada;

    private Long Director_curso;

    private Long nivelAcademico;

    @Override
    public Curso toEntity() {
        return Curso.builder()
                .id(id)
                .nombre(nombre)
                .jornada(jornada)
                .build();

    }

    @Override
    public void fromEntity(Curso entity) {
        this.id = entity.getId();
        this.nombre = entity.getNombre();
        this.jornada = entity.getJornada();
        this.Director_curso = entity.getProfesorid()!= null ? entity.getProfesorid().getId() : null;
        this.nivelAcademico = entity.getNivelAcademico() != null ? entity.getNivelAcademico().getId() : null;

    }

    @Override
    public void updateEntity(Curso entity) {
        if (this.nombre != null) {
            entity.setNombre(this.nombre);
        }
        if (this.jornada != null) {
            entity.setJornada(this.jornada);
        }
        
    }

}
