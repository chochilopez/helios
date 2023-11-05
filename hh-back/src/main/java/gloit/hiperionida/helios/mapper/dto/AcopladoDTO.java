package gloit.hiperionida.helios.mapper.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AcopladoDTO extends AbsVehiculoDTO implements Serializable {
    private String id;
    private List<NeumaticoDTO> neumaticos;
    private SeguroDTO seguro;
        /*
        private Long id;
    private Long seguro_id;
     */
}
