package gloit.hiperionida.helios.util.mapper.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UsuarioDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String nombre;
    private String dni;
    private String direccion;
    private String telefono;
    private String username;
    private List<String> roles = new ArrayList<>();
}
