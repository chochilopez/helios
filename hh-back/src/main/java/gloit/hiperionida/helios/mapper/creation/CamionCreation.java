package gloit.hiperionida.helios.mapper.creation;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CamionCreation extends AbsVehiculoCreation {
    private String numeroChasis;
    private String numeroMotor;

    public CamionCreation() {
    }

    public CamionCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String anio, String cantidadNeumaticos, String marcaModelo, String notas, String patente, String peso, String numeroChasis, String numeroMotor) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada, anio, cantidadNeumaticos, marcaModelo, notas, patente, peso);
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
    }
}
