package gloit.hiperionida.helios.mapper.creation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AcopladoCreation extends AbsVehiculoCreation {
    private String id;
    private List<String> neumaticos_id;
    private String seguro_id;
}
