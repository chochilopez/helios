package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CombustibleDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String fecha;
    private String kilometros;
    private String litros;
    private String precio;
    private String notas;
    private String camion_id;
    private String conductor_id;
    private String proveedor_id;
}
