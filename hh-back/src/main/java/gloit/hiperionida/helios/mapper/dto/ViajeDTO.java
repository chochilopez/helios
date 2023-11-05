package gloit.hiperionida.helios.mapper.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ViajeDTO extends AbsViajeDTO implements Serializable {
    private String id;
    private String guia;
    private ConductorDTO conductor;
    private CamionDTO camion;
    private AcopladoDTO acoplado;
    private List<GastoDTO> gastos;
    /*
        private Long id;
    private String guia;

    private Long conductor_id;
    private Long camion_id;
    private Long acoplado_id;
     */
}
