/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.DTO;

import com.School_System.app.Model.User;

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
public class UserDTO implements Request<User>, Response<User> {

    private Long id;
    private String usuario;
    private String rol;
    private Boolean estado;
    private Long profesorId; 

    @Override
    public User toEntity() {
        return User.builder()
                .id(id)
                .usuario(usuario)
                .rol(rol)
                .estado(estado != null ? estado : true)
                .build();
    }

    @Override
    public void fromEntity(User entity) {
        this.id = entity.getId();
        this.usuario = entity.getUsuario();
        this.rol = entity.getRol();
        this.estado = entity.getEstado();
        this.profesorId = entity.getProfesor() != null ? entity.getProfesor().getId() : null;
    }

    @Override
    public void updateEntity(User user) {
        if (usuario != null) user.setUsuario(usuario);
        if (rol != null) user.setRol(rol);
        if (estado != null) user.setEstado(estado);
    }
}

