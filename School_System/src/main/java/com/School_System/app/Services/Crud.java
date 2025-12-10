/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import com.School_System.app.DTO.Request;
import com.School_System.app.DTO.Response;

public abstract class Crud<T, ID, Req extends Request<T>, Res extends Response<T>>
        implements Services<T, ID, Req, Res> {

    protected abstract JpaRepository<T, ID> getJpaRepository();
    public abstract Res createResponse(T entity);

  //  protected abstract Res createResponse(T entity);

    @Override
    public T create(Req request) {
        T entity = request.toEntity();
        return getJpaRepository().save(entity);
    }

    @Override
    public List<Res> select() {
        return getJpaRepository().findAll().stream()
                .map(this::createResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Res buscar(ID id) {
        T entity = getJpaRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        return createResponse(entity);
    }

    @Override
    public T update(ID id, Req request) {
        T entity = getJpaRepository().findById(id)
            .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        // Actualizar solo los campos que vienen en el DTO
        request.updateEntity(entity); // <- este método debe existir en tu DTO
       
        return getJpaRepository().save(entity);
    }

    @Override
    public void desactivar(ID id) {
        T entity = getJpaRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
 
    }
}
