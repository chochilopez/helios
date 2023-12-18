package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class FacturaCreation extends AbsAuditoriaCreation {
    private String bonificacion;
    private String cantidad;
    private String clienteId;
    private String codigo;
    private String concepto;
    private String condicionPago;
    private String domicilioComercial;
    private String fechaEmision;
    private String fechaVencimiento;
    private String fechaVencimientoId;
    private String iva;
    private String notas;
    private String numeroComprobante;
    private String otrosImpuestos;
    private String pagada;
    private String precioUnitario;
    private String razonSocial;
    private String remitoId;
    private String subTotal;
    private String tipoComprobante;
    private String viajeId;

    public FacturaCreation() {
    }

    public FacturaCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String bonificacion, String cantidad, String clienteId, String codigo, String concepto, String condicionPago, String domicilioComercial, String fechaEmision, String fechaVencimiento, String fechaVencimientoId, String iva, String notas, String numeroComprobante, String otrosImpuestos, String pagada, String precioUnitario, String razonSocial, String remitoId, String subTotal, String tipoComprobante, String viajeId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.bonificacion = bonificacion;
        this.cantidad = cantidad;
        this.clienteId = clienteId;
        this.codigo = codigo;
        this.concepto = concepto;
        this.condicionPago = condicionPago;
        this.domicilioComercial = domicilioComercial;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaVencimientoId = fechaVencimientoId;
        this.iva = iva;
        this.notas = notas;
        this.numeroComprobante = numeroComprobante;
        this.otrosImpuestos = otrosImpuestos;
        this.pagada = pagada;
        this.precioUnitario = precioUnitario;
        this.razonSocial = razonSocial;
        this.remitoId = remitoId;
        this.subTotal = subTotal;
        this.tipoComprobante = tipoComprobante;
        this.viajeId = viajeId;
    }
}