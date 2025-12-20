/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.DTO;

import com.School_System.app.Model.Asignatura;
import com.School_System.app.Model.Curso;
import com.School_System.app.Model.Profesor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author yesec
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaDTO implements Request<Asignatura>, Response<Asignatura> {

    private Long id;

    private String nombre;

    private Long cursoId;

    private Long profesorId;



    @Override
    public Asignatura toEntity() {
           Asignatura asignatura = Asignatura.builder()
                .id(id)
                .nombre(nombre)
                .build();

        if (this.cursoId != null) {
            Curso curso = new Curso();
            curso.setId(this.cursoId);
            asignatura.setCurso(curso);
        }

        if (this.profesorId != null) {
            Profesor profesor = new Profesor();
            profesor.setId(this.profesorId);
            asignatura.setProfesor(profesor);
        }

        return asignatura;
    }



    @Override
    public void fromEntity(Asignatura entity) {
        this.id = entity.getId();
        this.nombre = entity.getNombre();
        this.cursoId = entity.getCurso() != null ? entity.getCurso().getId() : null;
        this.profesorId = entity.getProfesor() != null ? entity.getProfesor().getId() : null;
    }

    @Override
    public void updateEntity(Asignatura entity) {

        if (this.nombre != null) {
            entity.setNombre(this.nombre);
        }

    }

}
