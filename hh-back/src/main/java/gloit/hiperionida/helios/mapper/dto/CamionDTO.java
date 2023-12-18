package gloit.hiperionida.helios.mapper.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CamionDTO extends AbsVehiculoDTO implements Serializable {
    private String numeroChasis;
    private String numeroMotor;

    public CamionDTO() {
    }

    public CamionDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String anio, String cantidadNeumaticos, String marcaModelo, String notas, String patente, String peso, String numeroChasis, String numeroMotor) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId, anio, cantidadNeumaticos, marcaModelo, notas, patente, peso);
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
    }
}
