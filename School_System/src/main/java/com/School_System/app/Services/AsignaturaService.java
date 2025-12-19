/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Services;


import com.School_System.app.DTO.AsignaturaDTO;
import com.School_System.app.Model.Asignatura;
import com.School_System.app.Repository.AsignaturaRepository;
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
public class AsignaturaService extends Crud<Asignatura, Long, AsignaturaDTO, AsignaturaDTO> {
    private final AsignaturaRepository asignaturaRepository;
    @Override
    protected JpaRepository<Asignatura, Long> getJpaRepository() {
        return asignaturaRepository;
    }

    @Override
    public AsignaturaDTO createResponse(Asignatura entity) {
        AsignaturaDTO dto = AsignaturaDTO.builder().build();
        dto.fromEntity(entity);
        return dto;
        
    }     @Transactional
    @Override
    public void desactivar(Long id) {
         getJpaRepository().deleteById(id);
    }
    
    
}
