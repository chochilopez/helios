package gloit.hiperionida.helios.mapper.creation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CamionCreation extends AbsVehiculoCreation {
    private String numeroChasis;
    private String numeroMotor;
}
