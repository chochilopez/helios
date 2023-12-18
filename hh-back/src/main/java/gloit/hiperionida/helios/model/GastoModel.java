package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;

import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "gasto")
public class GastoModel extends AbsAuditoriaModel {
    private Double monto;
    @Column(columnDefinition = "TEXT")
    private String notas;

    private Long categoriaGastoId;
    private Long viajeId;

    public GastoModel() {
    }

    public GastoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, Double monto, String notas, Long categoriaGastoId, Long viajeId) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.monto = monto;
        this.notas = notas;
        this.categoriaGastoId = categoriaGastoId;
        this.viajeId = viajeId;
    }
}

