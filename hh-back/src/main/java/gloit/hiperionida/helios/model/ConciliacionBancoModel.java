package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.MovimientoEnum;
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
@Table(name = "conciliacion_banco")
public class ConciliacionBancoModel extends AbsAuditoriaModel {
    @Enumerated(EnumType.STRING)
    private MovimientoEnum movimiento;
    private LocalDateTime fecha;
    @Column(columnDefinition = "TEXT")
    private String concepto;
    private Double monto;

    private Long bancoId;

    public ConciliacionBancoModel() {
    }

    public ConciliacionBancoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, MovimientoEnum movimiento, LocalDateTime fecha, String concepto, Double monto, Long bancoId) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.movimiento = movimiento;
        this.fecha = fecha;
        this.concepto = concepto;
        this.monto = monto;
        this.bancoId = bancoId;
    }
}
