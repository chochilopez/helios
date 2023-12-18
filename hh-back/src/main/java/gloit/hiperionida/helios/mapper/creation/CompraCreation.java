package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CompraCreation extends AbsAuditoriaCreation {
    private String fecha;
    private String iva;
    private String numeroComprobante;
    private String subTotal;
    private String tipoComprobante;
    private String notas;

    private String comprobanteId;
    private String proveedorId;

    public CompraCreation() {
    }

    public CompraCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String fecha, String iva, String numeroComprobante, String subTotal, String tipoComprobante, String notas, String comprobanteId, String proveedorId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
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
