package gloit.hiperionida.helios.mapper.dto;

import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Setter
public class CamionDTO extends AbsVehiculoDTO implements Serializable {
    private String id;
    private String numeroChasis;
    private String numeroMotor;
    private String pesoArrastre;
    private List<NeumaticoDTO> neumaticos;
    private SeguroDTO seguro;
    private List<ServicioDTO> servicios;
}
