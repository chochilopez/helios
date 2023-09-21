package gloit.hiperionida.helios.mapper.dto;

import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Setter
public class ViajeDTO extends AbsViajeDTO implements Serializable {
    private String id;
    private String guia;
    private ConductorDTO conductor;
    private CamionDTO camion;
    private AcopladoDTO acoplado;
    private List<GastoDTO> gastos;
}
