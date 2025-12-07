/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.DTO;

import com.School_System.app.Model.Profesor;
import com.School_System.app.Model.*;

/**
 *
 * @author yesec
 */
public class UserDTO implements Request<User> , Response<User>{
    private Long id;
    private String usuario;
    private String contraseña;
    private String rol;
    private boolean estado;
    private String id_teacher;
    
    @Override
    public User toEntity() {
        return User.builder()
                //.id(id)
                .id(id)
                .usuario(usuario)
                .contrasena(contraseña)
                .estado(estado)
                .rol(rol)
                .build();
    }

    @Override
    public void fromEntity(User entity) {
        //this.id = entity.getId();
        this.contraseña = entity.getContrasena();
        this.estado = entity.getEstado();
        this.rol = entity.getRol();
        this.usuario = entity.getUsuario();
        this.estado = entity.getEstado();
    }
    
    @Override
    public void updateEntity(User user) {
    if(this.usuario != null) user.setUsuario(this.usuario);
    if(this.contraseña != null) user.setContrasena(this.contraseña);
    if(this.estado != true) user.setEstado(this.estado);
}
}
