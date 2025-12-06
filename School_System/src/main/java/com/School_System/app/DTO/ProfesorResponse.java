
package com.School_System.app.DTO;

/**
 *
 * @author yesec
 */


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfesorResponse {
    private Long id;
    private String numeroIdentificacion;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correoInstitucional;
    private Boolean activo;  
}
