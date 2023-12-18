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
@Table(name = "recibo")
public class ReciboModel extends AbsAuditoriaModel {
    private Double monto;
    private LocalDateTime fecha;

    public ReciboModel() {
    }

    public ReciboModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, Double monto, LocalDateTime fecha) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.monto = monto;
        this.fecha = fecha;
    }
}

