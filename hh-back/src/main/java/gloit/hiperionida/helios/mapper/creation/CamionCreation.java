package gloit.hiperionida.helios.mapper.creation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CamionCreation extends AbsVehiculoCreation {
    private String id;
    private String numeroChasis;
    private String numeroMotor;
    private String pesoArrastre;
    private String seguro_id;
    private List<String> servicios_id;
    private List<String> neumaticos_id;

    /*
        private Long id;
    private String numeroChasis;
    private String numeroMotor;
    private String pesoArrastre;

    private Long seguro_id;
     */
}
