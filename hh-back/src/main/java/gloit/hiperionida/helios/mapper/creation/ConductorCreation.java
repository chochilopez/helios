package gloit.hiperionida.helios.mapper.creation;

import lombok.Getter;
import java.util.List;

@Getter
public class ConductorCreation extends AbsPersonaCreation {
    private String id;
    private List<String> adelantos_id;
    private String camion_id;
    private String licencia_id;
}
