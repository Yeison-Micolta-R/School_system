/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.DTO;

import com.School_System.app.Model.Asignatura;

/**
 *
 * @author yesec
 */
public class AsignaturaDTO implements Request<Asignatura>, Response<Asignatura> {

    private Long id;

    private String nombre;

    private String curso;

    private String profesor;



    @Override
    public Asignatura toEntity() {
        return Asignatura.builder()
                .id(id)
                .nombre(nombre)
                .build();
    }

    @Override
    public void fromEntity(Asignatura entity) {
        this.id = entity.getId();
        this.nombre = entity.getNombre();
        this.curso = entity.getCurso() != null ? entity.getCurso().getId().toString() : null;
        this.profesor = entity.getProfesor() != null ? entity.getProfesor().getId().toString() : null;
    }

    @Override
    public void updateEntity(Asignatura entity) {

        if (this.nombre != null) {
            entity.setNombre(this.nombre);
        }

    }

}
