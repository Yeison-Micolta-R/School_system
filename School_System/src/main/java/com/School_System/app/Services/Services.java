/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.School_System.app.Services;

import java.util.List;

import com.School_System.app.DTO.Request;
import com.School_System.app.DTO.Response;

public interface Services<T, ID, Req extends Request<T>, Res extends Response<T>> {

    T create(Req request);

    List<Res> select();

    Res buscar(ID id);

    T update(ID id, Req request);

    void desactivar(ID id);
}
