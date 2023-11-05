package gloit.hiperionida.helios.util.mapper.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ArchivoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String descripcion;
    private String nombre;
    private String path;
    private String tamanio;
    private String tipo;
}