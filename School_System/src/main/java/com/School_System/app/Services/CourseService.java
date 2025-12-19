/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Services;

import com.School_System.app.DTO.CursoDTO;
import com.School_System.app.Model.Curso;
import com.School_System.app.Repository.CourseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.School_System.app.Repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yesec
 */
@Service
@RequiredArgsConstructor
public class CourseService extends Crud<Curso, Long, CursoDTO, CursoDTO> {
    private final CourseRepository courseRepository;

    @Override
    protected JpaRepository<Curso, Long> getJpaRepository() {
        return courseRepository;
    }

    @Override
    public CursoDTO createResponse(Curso entity) {
        CursoDTO dto = CursoDTO.builder().build();
        dto.fromEntity(entity);
        return dto;
    }
      @Transactional
    @Override
    public void desactivar(Long id) {
         getJpaRepository().deleteById(id);
    }
    
    
}
