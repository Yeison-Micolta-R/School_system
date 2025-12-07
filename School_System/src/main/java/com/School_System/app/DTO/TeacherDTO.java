/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.DTO;

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
public class TeacherDTO implements Request<Profesor>, Response<Profesor> {

    private Long id;
    private String numeroIdentificacion;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correoInstitucional;
    private Boolean estado;

    @Override
    public Profesor toEntity() {
        return Profesor.builder()
                .id(id)
                .numeroIdentificacion(numeroIdentificacion)
                .nombre(nombre)
                .apellido(apellido)
                .telefono(telefono)
                .correoInstitucional(correoInstitucional)
                .estado(estado != null ? estado : true)
                .build();
    }

    @Override
    public void fromEntity(Profesor entity) {
        this.id = entity.getId();
        this.numeroIdentificacion = entity.getNumeroIdentificacion();
        this.nombre = entity.getNombre();
        this.apellido = entity.getApellido();
        this.telefono = entity.getTelefono();
        this.correoInstitucional = entity.getCorreoInstitucional();
        this.estado = entity.getEstado();
    }

    @Override
    public void updateEntity(Profesor profesor) {
        if (this.nombre != null) {
            profesor.setNombre(this.nombre);
        }
        if (this.apellido != null) {
            profesor.setApellido(this.apellido);
        }
        if (this.telefono != null) {
            profesor.setTelefono(this.telefono);
        }
        if (this.correoInstitucional != null) {
            profesor.setCorreoInstitucional(this.correoInstitucional);
        }
        if (this.numeroIdentificacion != null) {
            profesor.setNumeroIdentificacion(this.numeroIdentificacion);
        }
        if (this.estado != null) {
            profesor.setEstado(this.estado);
        }
    }

}
