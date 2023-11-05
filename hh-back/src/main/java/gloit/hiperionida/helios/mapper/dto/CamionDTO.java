package gloit.hiperionida.helios.mapper.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CamionDTO extends AbsVehiculoDTO implements Serializable {
    private String id;
    private String numeroChasis;
    private String numeroMotor;
    private String pesoArrastre;
    private SeguroDTO seguro;
    private List<ServicioDTO> servicios;
    private List<NeumaticoDTO> neumaticos;
    /*
        private Long id;
    private String numeroChasis;
    private String numeroMotor;
    private String pesoArrastre;

    private Long seguro_id;
     */
}
