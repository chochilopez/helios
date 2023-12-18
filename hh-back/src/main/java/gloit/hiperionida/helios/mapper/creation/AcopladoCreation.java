package gloit.hiperionida.helios.mapper.creation;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class AcopladoCreation extends AbsVehiculoCreation {
    public AcopladoCreation() {
    }

    public AcopladoCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String anio, String cantidadNeumaticos, String marcaModelo, String notas, String patente, String peso) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada, anio, cantidadNeumaticos, marcaModelo, notas, patente, peso);
    }
}
