package com.School_System.app.DTO;

import com.School_System.app.Model.Estudiante;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteDTO implements Request<Estudiante>, Response<Estudiante> {
     // Campos heredados de PersonaBase
    private Long id;
    private String nombreCompleto;
    private String apellidosCompletos;
    private String tipoDocumentoIdentidad;
    private String numeroDocumentoIdentidad;
    private LocalDate fechaNacimiento;
    private String generoSexo;
    private String nacionalidad;
    private String direccionResidencia;
    private String ciudadResidencia;
    private String correoElectronico;
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
    private LocalDate fechaIngreso;
    private LocalDate fechaMatricula;
    private Long cursoId; // ID del curso al que pertenece el estudiante
    private String notas; // Notas adicionales sobre el estudiante
  
    @Override
    public Estudiante toEntity() {
        Estudiante estudiante = Estudiante.builder()
        .nombreCompleto(this.nombreCompleto)
        .apellidosCompletos(this.apellidosCompletos)
         .tipoDocumentoIdentidad(this.tipoDocumentoIdentidad)
        .numeroDocumentoIdentidad(this.numeroDocumentoIdentidad)
        .fechaNacimiento(java.sql.Date.valueOf(this.fechaNacimiento)) 
        .generoSexo(this.generoSexo)
        .nacionalidad(this.nacionalidad)
        .direccionResidencia(this.direccionResidencia)
        .ciudadResidencia(this.ciudadResidencia)
        .correoElectronico(this.correoElectronico)
        .telefonoCelular(this.telefonoCelular)
        .telefonoSecundario(this.telefonoSecundario)
        .contactoEmergenciaNombre(this.contactoEmergenciaNombre)
        .contactoEmergenciaParentesco(this.contactoEmergenciaParentesco)
        .contactoEmergenciaTelefono(this.contactoEmergenciaTelefono)
        .contactoEmergenciaCorreo(this.contactoEmergenciaCorreo)
        .condicionesEspeciales(this.condicionesEspeciales)  
        .tipoSangre(this.tipoSangre)
        .estado(this.estado)
        .fechaIngreso(java.sql.Date.valueOf(this.fechaIngreso))
        .fechaMatricula(java.sql.Date.valueOf(this.fechaMatricula))
        .build(); 
            
        return estudiante;
    }

    @Override
    public void fromEntity(Estudiante entity) {
        this.id = entity.getId();
        this.nombreCompleto = entity.getNombreCompleto();
        this.apellidosCompletos = entity.getApellidosCompletos();
        this.tipoDocumentoIdentidad = entity.getTipoDocumentoIdentidad();
        this.numeroDocumentoIdentidad = entity.getNumeroDocumentoIdentidad();
        this.fechaNacimiento = entity.getFechaNacimiento().toLocalDate();
        this.generoSexo = entity.getGeneroSexo();
        this.nacionalidad = entity.getNacionalidad();
        this.direccionResidencia =  entity.getDireccionResidencia();
        this.ciudadResidencia = entity.getCiudadResidencia();
        this.correoElectronico = entity.getCorreoElectronico();
        this.telefonoCelular = entity.getTelefonoCelular();
        this.telefonoSecundario = entity.getTelefonoSecundario();
        this.contactoEmergenciaNombre = entity.getContactoEmergenciaNombre();
        this.contactoEmergenciaParentesco = entity.getContactoEmergenciaParentesco();
        this.contactoEmergenciaTelefono = entity.getContactoEmergenciaTelefono();
        this.tipoSangre = entity.getTipoSangre();
        this.estado = entity.getEstado();
        this.fechaIngreso = entity.getFechaIngreso().toLocalDate();
        this.fechaMatricula = entity.getFechaMatricula().toLocalDate();
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
        if (this.numeroDocumentoIdentidad != null) {
            estudiante.setNumeroDocumentoIdentidad(this.numeroDocumentoIdentidad);
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
        if (this.correoElectronico != null) {
            estudiante.setCorreoElectronico(this.correoElectronico);
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
            estudiante.setFechaIngreso(java.sql.Date.valueOf(this.fechaIngreso));
        }
        if (this.fechaMatricula != null) {
            estudiante.setFechaMatricula(java.sql.Date.valueOf(this.fechaMatricula));
        }
    
     
        // El curso dirigido debe manejarse aparte en el service
    }

}
