/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Services;

import com.School_System.app.DTO.NotaDTO;
import com.School_System.app.Model.Nota;
import com.School_System.app.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yesec
 */
@Service
@RequiredArgsConstructor
public class NotaService extends Crud<Nota, Long, NotaDTO, NotaDTO> {
    private final NotaRepository notaRepositorio;
    

    
    @Override
    public NotaDTO createResponse(Nota entity) {
        NotaDTO dto = NotaDTO.builder().build();
        dto.fromEntity(entity);
        return dto;
    }
    @Override
    protected JpaRepository<Nota, Long> getJpaRepository() {
        return notaRepositorio;
    }
    @Transactional
    @Override
    public void desactivar(Long id) {
         getJpaRepository().deleteById(id);
    }
    
}
