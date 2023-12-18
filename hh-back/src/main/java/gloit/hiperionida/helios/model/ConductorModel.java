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
@Table(name = "conductor")
public class ConductorModel extends AbsPersonaModel {
    public ConductorModel() {
    }

    public ConductorModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String direccion, String email, String identificacion, String nombre, String notas, String telefono) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada, direccion, email, identificacion, nombre, notas, telefono);
    }
}

