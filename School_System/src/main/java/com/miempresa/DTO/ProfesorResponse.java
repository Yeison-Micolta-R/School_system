
package com.miempresa.DTO;

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
