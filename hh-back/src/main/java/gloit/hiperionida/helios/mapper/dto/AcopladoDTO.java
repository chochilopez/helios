package gloit.hiperionida.helios.mapper.dto;

import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Setter
public class AcopladoDTO extends AbsVehiculoDTO implements Serializable {
    private String id;
    private List<NeumaticoDTO> neumaticos;
    private SeguroDTO seguro;
}
