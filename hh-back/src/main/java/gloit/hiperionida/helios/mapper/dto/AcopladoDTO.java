package gloit.hiperionida.helios.mapper.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class AcopladoDTO extends AbsVehiculoDTO implements Serializable {
    public AcopladoDTO() {
    }

    public AcopladoDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String anio, String cantidadNeumaticos, String marcaModelo, String notas, String patente, String peso) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId, anio, cantidadNeumaticos, marcaModelo, notas, patente, peso);
    }
}
