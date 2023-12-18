package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class PagoDTO extends AbsAuditoriaDTO implements Serializable {
    private String monto;
    private String notas;
    private String tipoPago;
    private String compraId;
    private String facturaId;
    private String recibo;

    public PagoDTO() {
    }

    public PagoDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String monto, String notas, String tipoPago, String compraId, String facturaId, String recibo) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.monto = monto;
        this.notas = notas;
        this.tipoPago = tipoPago;
        this.compraId = compraId;
        this.facturaId = facturaId;
        this.recibo = recibo;
    }
}
