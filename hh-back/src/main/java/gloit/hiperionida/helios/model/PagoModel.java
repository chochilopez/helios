package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
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
@Table(name = "pago")
public class PagoModel extends AbsAuditoriaModel {
    private Double monto;
    @Column(columnDefinition = "TEXT")
    private String notas;
    @Enumerated(EnumType.STRING)
    private TipoPagoEnum tipoPago;

    private Long clienteId;
    private Long reciboId;

    public PagoModel() {
    }

    public PagoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, Double monto, String notas, TipoPagoEnum tipoPago, Long clienteId, Long reciboId) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.monto = monto;
        this.notas = notas;
        this.tipoPago = tipoPago;
        this.clienteId = clienteId;
        this.reciboId = reciboId;
    }
}

