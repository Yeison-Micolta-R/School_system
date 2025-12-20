package com.School_System.app.DTO;

import java.util.Date;

import com.School_System.app.Model.Curso;
import com.School_System.app.Model.Estudiante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstudianteDTO implements Request<Estudiante>, Response<Estudiante> {
     // Campos heredados de PersonaBase
    private Long id;
    private String nombreCompleto;
    private String apellidosCompletos;
    private String tipoDocumentoIdentidad;
    private String nDocIden;
    private Date fechaNacimiento;
    private String generoSexo;
    private String nacionalidad;
    private String direccionResidencia;
    private String ciudadResidencia;
    private String correoInstitucional;
    private String telefonoCelular;
    private String telefonoSecundario;
    private String contactoEmergenciaNombre;
    private String contactoEmergenciaParentesco;
    private String contactoEmergenciaTelefono;
    private String contactoEmergenciaCorreo;
    private String condicionesEspeciales;
   // private String alergias;
    private String tipoSangre;
    private Boolean estado;
   

    // Campos específicos de Estudiante
    private Date fechaIngreso;
    private Date fechaMatricula;
    private Long cursoId; // ID del curso al que pertenece el estudiante
  
    @Override
    public Estudiante toEntity() {
        Estudiante estudiante = Estudiante.builder()   
        .nombreCompleto(this.nombreCompleto)
        .apellidosCompletos(this.apellidosCompletos)
        .tipoDocumentoIdentidad(this.tipoDocumentoIdentidad)
        .nDocIden(this.nDocIden)
        .fechaNacimiento(this.fechaNacimiento) 
        .generoSexo(this.generoSexo)
        .nacionalidad(this.nacionalidad)
        .direccionResidencia(this.direccionResidencia)
        .ciudadResidencia(this.ciudadResidencia)
        .correoInstitucional(this.correoInstitucional) 
        .telefonoCelular(this.telefonoCelular)
        .telefonoSecundario(this.telefonoSecundario)
        .contactoEmergenciaNombre(this.contactoEmergenciaNombre)
        .contactoEmergenciaParentesco(this.contactoEmergenciaParentesco)
        .contactoEmergenciaTelefono(this.contactoEmergenciaTelefono)
        .condicionesMedicasRelevantes(condicionesEspeciales) 
        .tipoSangre(this.tipoSangre)
        .estado(this.estado)
         
        .fechaIngreso((this.fechaIngreso))
        .fechaMatricula((this.fechaMatricula))
        .build(); 
         // Aquí conviertes cursoId en un objeto Curso
          
    if (this.cursoId != null) {
        Curso curso = new Curso();
        curso.setId(this.cursoId);
        estudiante.setCurso(curso);
    }


            
        return estudiante;
    }

    @Override
    public void fromEntity(Estudiante entity) {
        this.id = entity.getId();
        this.nombreCompleto = entity.getNombreCompleto();
        this.apellidosCompletos = entity.getApellidosCompletos();
        this.tipoDocumentoIdentidad = entity.getTipoDocumentoIdentidad();
        this.nDocIden = entity.getNDocIden();
        this.fechaNacimiento = entity.getFechaNacimiento();
        this.generoSexo = entity.getGeneroSexo();
        this.nacionalidad = entity.getNacionalidad();
        this.direccionResidencia =  entity.getDireccionResidencia();
        this.ciudadResidencia = entity.getCiudadResidencia();
        this.correoInstitucional = entity.getCorreoInstitucional();
        this.telefonoCelular = entity.getTelefonoCelular();
        this.telefonoSecundario = entity.getTelefonoSecundario();
        this.contactoEmergenciaNombre = entity.getContactoEmergenciaNombre();
        this.contactoEmergenciaParentesco = entity.getContactoEmergenciaParentesco();
        this.contactoEmergenciaTelefono = entity.getContactoEmergenciaTelefono();
        this.tipoSangre = entity.getTipoSangre();
        this.estado = entity.getEstado();
        this.fechaIngreso = entity.getFechaIngreso();
        this.fechaMatricula = entity.getFechaMatricula();
        if (entity.getCurso() != null) {
            this.cursoId =  entity.getCurso().getId();
        }
        
    }

    @Override
    public void updateEntity(Estudiante estudiante) {
        if (this.nombreCompleto != null) {
            estudiante.setNombreCompleto(this.nombreCompleto);
        }
        if (this.apellidosCompletos != null) {
            estudiante.setApellidosCompletos(this.apellidosCompletos);
        }
        if (this.tipoDocumentoIdentidad != null) {
            estudiante.setTipoDocumentoIdentidad(this.tipoDocumentoIdentidad);
        }
        if (this.nDocIden != null) {
            estudiante.setNDocIden(this.nDocIden);
        }
        if (this.fechaNacimiento != null) {
            estudiante.setFechaNacimiento(this.fechaNacimiento);
        }
        if (this.generoSexo != null) {
            estudiante.setGeneroSexo(this.generoSexo);
        }
        if (this.nacionalidad != null) {
            estudiante.setNacionalidad(this.nacionalidad);
        }
        if (this.direccionResidencia != null) {
            estudiante.setDireccionResidencia(this.direccionResidencia);
        }
        if (this.ciudadResidencia != null) {
            estudiante.setCiudadResidencia(this.ciudadResidencia);
        }
        if (this.correoInstitucional != null) {
            estudiante.setCorreoInstitucional(this.correoInstitucional);
        }
        if (this.telefonoCelular != null) {
            estudiante.setTelefonoCelular(this.telefonoCelular);
        }
        if (this.telefonoSecundario != null) {
            estudiante.setTelefonoSecundario(this.telefonoSecundario);
        }
        if (this.contactoEmergenciaNombre != null) {
            estudiante.setContactoEmergenciaNombre(this.contactoEmergenciaNombre);
        }
        if (this.contactoEmergenciaParentesco != null) {
            estudiante.setContactoEmergenciaParentesco(this.contactoEmergenciaParentesco);
        }
        if (this.contactoEmergenciaTelefono != null) {
            estudiante.setContactoEmergenciaTelefono(this.contactoEmergenciaTelefono);
        }
       
       
        if (this.tipoSangre != null) {
            estudiante.setTipoSangre(this.tipoSangre);
        }
        if (this.estado != null) {
            estudiante.setEstado(this.estado);
        }
        if (this.fechaIngreso != null) {
            estudiante.setFechaIngreso((this.fechaIngreso));
        }
        if (this.fechaMatricula != null) {
            estudiante.setFechaMatricula((this.fechaMatricula));
        }
    
     
    }

}
