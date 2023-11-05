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
    private String seguro_id;
}
