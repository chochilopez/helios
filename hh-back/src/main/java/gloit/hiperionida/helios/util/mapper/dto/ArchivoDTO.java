package gloit.hiperionida.helios.util.mapper.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ArchivoDTO implements Serializable {
    private String id;
    private String path;
    private String nombre;
}
