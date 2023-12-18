package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CompraDTO extends AbsAuditoriaDTO implements Serializable {
    private String fecha;
    private String iva;
    private String numeroComprobante;
    private String subTotal;
    private String tipoComprobante;
    private String notas;
    private String comprobante;
    private String proveedor;

    public CompraDTO() {
    }

    public CompraDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String fecha, String iva, String numeroComprobante, String subTotal, String tipoComprobante, String notas, String comprobante, String proveedor) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.fecha = fecha;
        this.iva = iva;
        this.numeroComprobante = numeroComprobante;
        this.subTotal = subTotal;
        this.tipoComprobante = tipoComprobante;
        this.notas = notas;
        this.comprobante = comprobante;
        this.proveedor = proveedor;
    }
}
