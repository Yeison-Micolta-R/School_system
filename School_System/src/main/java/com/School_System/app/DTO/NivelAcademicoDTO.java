/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.DTO;

import com.School_System.app.Model.NivelAcademico;

/**
 *
 * @author yesec
 */
public class NivelAcademicoDTO implements Request<NivelAcademico>, Response<NivelAcademico> {
    private Long id;
    private String nombre;

    @Override
    public NivelAcademico toEntity() {
        return NivelAcademico.builder()
                .id(id)
                .nombre(nombre)
                .build();
    }



    @Override
    public void fromEntity(NivelAcademico entity) {
        this.id = entity.getId();
        this.nombre =  entity.getNombre();
    }
        @Override
    public void updateEntity(NivelAcademico entity) {
            if (this.nombre != null) {
                this.nombre= entity.getNombre();
                
            }
    }
}
