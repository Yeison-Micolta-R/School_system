/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Services;

import com.School_System.app.DTO.NivelAcademicoDTO;
import com.School_System.app.DTO.Request;
import com.School_System.app.DTO.Response;
import com.School_System.app.Model.NivelAcademico;
import com.School_System.app.Repository.NivelAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author yesec
 */
@Service
@RequiredArgsConstructor
public class NivelAcademicoService extends Crud<NivelAcademico, Long, NivelAcademicoDTO, NivelAcademicoDTO> {

    private final NivelAcademicoRepository nivelAcademicoRepository;

    @Override
    protected JpaRepository<NivelAcademico, Long> getJpaRepository() {
        return nivelAcademicoRepository;
    }

    @Override
    public NivelAcademicoDTO createResponse(NivelAcademico entity) {
        NivelAcademicoDTO dto = NivelAcademicoDTO.builder().build();
        dto.fromEntity(entity);
        return dto;

    }
 
}
