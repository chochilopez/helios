package gloit.hiperionida.helios.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "camion")
public class CamionModel extends AbsVehiculoModel {
    private String numeroChasis;
    private String numeroMotor;

    public CamionModel() {
    }

    public CamionModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, Integer anio, Integer cantidadNeumaticos, String marcaModelo, String notas, String patente, String peso, String numeroChasis, String numeroMotor) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada, anio, cantidadNeumaticos, marcaModelo, notas, patente, peso);
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
    }
}
