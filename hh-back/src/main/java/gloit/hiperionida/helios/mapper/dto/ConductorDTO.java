package gloit.hiperionida.helios.mapper.dto;

import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Setter
public class ConductorDTO extends AbsPersonaDTO implements Serializable {
    private String id;
    private List<AdelantoDTO> adelantos;
    private CamionDTO camion;
    private LicenciaDTO licencia;
}
