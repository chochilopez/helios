package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class PagoCreation extends AbsAuditoriaCreation {
    private String monto;
    private String notas;
    private String tipoPago;

    private String compraId;
    private String facturaId;
    private String reciboId;

    public PagoCreation() {
    }

    public PagoCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String monto, String notas, String tipoPago, String compraId, String facturaId, String reciboId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.monto = monto;
        this.notas = notas;
        this.tipoPago = tipoPago;
        this.compraId = compraId;
        this.facturaId = facturaId;
        this.reciboId = reciboId;
    }
}
