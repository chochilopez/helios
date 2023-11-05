package gloit.hiperionida.helios.mapper.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AcopladoDTO extends AbsVehiculoDTO implements Serializable {
    private String id;
    private String seguro_id;
}
