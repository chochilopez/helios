package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class FacturaDTO extends AbsAuditoriaDTO implements Serializable {
    private String bonificacionPercentil;
    private String bonificacionMonto;
    private String camion;
    private String cantidad;
    private String cantidadTransportada;
    private String categoriaViaje;
    private String codigo;
    private String comprobante;
    private String cliente;
    private String concepto;
    private String condicionPago;
    private String conductor;
    private String destino;
    private String domicilioComercial;
    private String fechaEmision;
    private String fechaViaje;
    private String fechaVencimiento;
    private String fechaVencimientoId;
    private String ivaPercentil;
    private String ivaMonto;
    private String kmCargado;
    private String notas;
    private String numeroComprobante;
    private String numeroGuia;
    private String numeroRemito;
    private String origen;
    private String otrosImpuestosPercentil;
    private String otrosImpuestosMonto;
    private String pagada;
    private String precioUnitario;
    private String razonSocial;
    private String subTotal;
    private String tipoComprobante;
    private String total;
    private String valorKm;
    private String vencida;

    public FacturaDTO() {
    }

    public FacturaDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String bonificacionPercentil, String bonificacionMonto, String camion, String cantidad, String cantidadTransportada, String categoriaViaje, String codigo, String comprobante, String cliente, String concepto, String condicionPago, String conductor, String destino, String domicilioComercial, String fechaEmision, String fechaViaje, String fechaVencimiento, String fechaVencimientoId, String ivaPercentil, String ivaMonto, String kmCargado, String notas, String numeroComprobante, String numeroGuia, String numeroRemito, String origen, String otrosImpuestosPercentil, String otrosImpuestosMonto, String pagada, String precioUnitario, String razonSocial, String subTotal, String tipoComprobante, String total, String valorKm, String vencida) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.bonificacionPercentil = bonificacionPercentil;
        this.bonificacionMonto = bonificacionMonto;
        this.camion = camion;
        this.cantidad = cantidad;
        this.cantidadTransportada = cantidadTransportada;
        this.categoriaViaje = categoriaViaje;
        this.codigo = codigo;
        this.comprobante = comprobante;
        this.cliente = cliente;
        this.concepto = concepto;
        this.condicionPago = condicionPago;
        this.conductor = conductor;
        this.destino = destino;
        this.domicilioComercial = domicilioComercial;
        this.fechaEmision = fechaEmision;
        this.fechaViaje = fechaViaje;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaVencimientoId = fechaVencimientoId;
        this.ivaPercentil = ivaPercentil;
        this.ivaMonto = ivaMonto;
        this.kmCargado = kmCargado;
        this.notas = notas;
        this.numeroComprobante = numeroComprobante;
        this.numeroGuia = numeroGuia;
        this.numeroRemito = numeroRemito;
        this.origen = origen;
        this.otrosImpuestosPercentil = otrosImpuestosPercentil;
        this.otrosImpuestosMonto = otrosImpuestosMonto;
        this.pagada = pagada;
        this.precioUnitario = precioUnitario;
        this.razonSocial = razonSocial;
        this.subTotal = subTotal;
        this.tipoComprobante = tipoComprobante;
        this.total = total;
        this.valorKm = valorKm;
        this.vencida = vencida;
    }
}