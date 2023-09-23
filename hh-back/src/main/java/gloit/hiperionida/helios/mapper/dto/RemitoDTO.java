package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RemitoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String descripcion;
    private String fecha;
    private String numero;
}
