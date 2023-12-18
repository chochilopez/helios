package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.CondicionPagoEnum;
import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import jakarta.persistence.*;
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
@Table(name = "factura")
public class FacturaModel extends AbsAuditoriaModel {
    private Double bonificacion;
    private Integer cantidad;
    private String codigo;
    @Column(columnDefinition = "TEXT")
    private String concepto;
    @Enumerated(EnumType.STRING)
    private CondicionPagoEnum condicionPago;
    private String domicilioComercial;
    private LocalDateTime fechaEmision;
    private Long fechaVencimientoId;
    private Double iva;
    private String notas;
    private String numeroComprobante;
    private Double otrosImpuestos;
    private Boolean pagada;
    private Double precioUnitario;
    private String razonSocial;
    private Long remitoId;
    @Enumerated(EnumType.STRING)
    private TipoComprobanteEnum tipoComprobante;
    private Double subTotal;
    private Long viajeId;

    public FacturaModel() {
    }

    public FacturaModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, Double bonificacion, Integer cantidad, String codigo, String concepto, CondicionPagoEnum condicionPago, String domicilioComercial, LocalDateTime fechaEmision, Long fechaVencimientoId, Double iva, String notas, String numeroComprobante, Double otrosImpuestos, Boolean pagada, Double precioUnitario, String razonSocial, Long remitoId, TipoComprobanteEnum tipoComprobante, Double subTotal, Long viajeId) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.bonificacion = bonificacion;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.concepto = concepto;
        this.condicionPago = condicionPago;
        this.domicilioComercial = domicilioComercial;
        this.fechaEmision = fechaEmision;
        this.fechaVencimientoId = fechaVencimientoId;
        this.iva = iva;
        this.notas = notas;
        this.numeroComprobante = numeroComprobante;
        this.otrosImpuestos = otrosImpuestos;
        this.pagada = pagada;
        this.precioUnitario = precioUnitario;
        this.razonSocial = razonSocial;
        this.remitoId = remitoId;
        this.tipoComprobante = tipoComprobante;
        this.subTotal = subTotal;
        this.viajeId = viajeId;
    }
}
