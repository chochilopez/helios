package gloit.hiperionida.helios.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "acoplado")
public class AcopladoModel extends AbsVehiculoModel {
    public AcopladoModel() {
    }

    public AcopladoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, Integer anio, Integer cantidadNeumaticos, String marcaModelo, String notas, String patente, String peso) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada, anio, cantidadNeumaticos, marcaModelo, notas, patente, peso);
    }
}
