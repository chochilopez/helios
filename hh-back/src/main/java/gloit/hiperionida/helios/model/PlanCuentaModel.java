package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "plan_cuenta")
public class PlanCuentaModel extends AbsAuditoriaModel {
    private String nombre;
    @Column(columnDefinition = "TEXT")
    private String notas;

    public PlanCuentaModel() {
    }

    public PlanCuentaModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String nombre, String notas) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.nombre = nombre;
        this.notas = notas;
    }
}
