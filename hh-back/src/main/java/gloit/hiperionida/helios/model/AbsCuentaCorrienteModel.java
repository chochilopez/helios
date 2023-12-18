package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.MovimientoEnum;
import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@Setter
@SuperBuilder
public abstract class AbsCuentaCorrienteModel extends AbsAuditoriaModel {
    private Double monto;
    @Column(columnDefinition = "TEXT")
    private String notas;
    @Enumerated(EnumType.STRING)
    private TipoPagoEnum tipoPago;
    @Enumerated(EnumType.STRING)
    private MovimientoEnum tipoMovimiento;
    private LocalDateTime fecha;

    public AbsCuentaCorrienteModel() {
    }

    public AbsCuentaCorrienteModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, Double monto, String notas, TipoPagoEnum tipoPago, MovimientoEnum tipoMovimiento, LocalDateTime fecha) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.monto = monto;
        this.notas = notas;
        this.tipoPago = tipoPago;
        this.tipoMovimiento = tipoMovimiento;
        this.fecha = fecha;
    }
}
