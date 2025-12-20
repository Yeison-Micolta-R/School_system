/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.DTO;

import com.School_System.app.Model.Asignatura;
import com.School_System.app.Model.Estudiante;
import com.School_System.app.Model.Nota;
import lombok.*;

/**
 *
 * @author yesec
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaDTO implements Request<Nota>, Response<Nota> {

    private Long id;
    private String periodo;
    private Double calificacion;
    private Long estudiante_id;
    private Long asignatura_id;

    @Override
    public Nota toEntity() {
        Nota nota = Nota.builder()
                .id(id)
                .periodo(periodo)
                .calificacion(calificacion)
                .build();
        
        if(this.asignatura_id != null){
            Asignatura asignatura = new Asignatura();
            asignatura.setId(this.asignatura_id);
            nota.setAsignatura(asignatura);
        }
          if(this.estudiante_id != null){
            Estudiante estudiante = new Estudiante();
            estudiante.setId(this.estudiante_id);
            nota.setEstudiante(estudiante);
        }
        return nota;
    }

    @Override
    public void fromEntity(Nota entity) {
        this.id = entity.getId();
        this.periodo = entity.getPeriodo();
        this.calificacion = entity.getCalificacion();
        this.estudiante_id = entity.getEstudiante() != null ? entity.getEstudiante().getId():null;
        this.asignatura_id = entity.getAsignatura() != null ? entity.getAsignatura().getId() : null;
    }

    @Override
    public void updateEntity(Nota entity) {
        if (this.periodo != null) {
            entity.setPeriodo(this.periodo);
        }
        if (this.calificacion != null) {
            entity.setCalificacion(this.calificacion);
        }
        

    }

}
