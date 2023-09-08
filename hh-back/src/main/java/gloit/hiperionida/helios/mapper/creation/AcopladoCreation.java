package gloit.hiperionida.helios.mapper.creation;

import lombok.Getter;
import java.util.List;

@Getter
public class AcopladoCreation extends AbsVehiculoCreation {
    private String id;
    private List<String> neumaticos_id;
    private String seguro_id;
}
