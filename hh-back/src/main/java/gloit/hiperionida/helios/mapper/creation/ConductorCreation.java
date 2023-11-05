package gloit.hiperionida.helios.mapper.creation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ConductorCreation extends AbsPersonaCreation {
    private String id;
    private List<String> adelantos_id;
    private String camion_id;
    private String licencia_id;
        /*
        private Long id;

    private Long camion_id;
    private Long licencia_id;
     */
}
