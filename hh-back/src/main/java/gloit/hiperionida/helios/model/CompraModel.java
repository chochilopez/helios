package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
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
@Table(name = "compra")
public class CompraModel extends AbsAuditoriaModel {
    private LocalDateTime fecha;
    private Double iva;
    private String numeroComprobante;
    private Double subTotal;
    @Enumerated(EnumType.STRING)
    private TipoComprobanteEnum tipoComprobante;
    @Column(columnDefinition = "TEXT")
    private String notas;

    private Long comprobanteId;
    private Long proveedorId;

    public CompraModel() {
    }

    public CompraModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, LocalDateTime fecha, Double iva, String numeroComprobante, Double subTotal, TipoComprobanteEnum tipoComprobante, String notas, Long comprobanteId, Long proveedorId) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.fecha = fecha;
        this.iva = iva;
        this.numeroComprobante = numeroComprobante;
        this.subTotal = subTotal;
        this.tipoComprobante = tipoComprobante;
        this.notas = notas;
        this.comprobanteId = comprobanteId;
        this.proveedorId = proveedorId;
    }
}
