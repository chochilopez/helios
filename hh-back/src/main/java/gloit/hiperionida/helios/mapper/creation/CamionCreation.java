package gloit.hiperionida.helios.mapper.creation;

import lombok.Getter;
import java.util.List;

@Getter
public class CamionCreation extends AbsVehiculoCreation {
    private String id;
    private String numeroChasis;
    private String numeroMotor;
    private String pesoArrastre;
    private String seguro_id;
    private List<String> servicios_id;
    private List<String> neumaticos_id;
}
