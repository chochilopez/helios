package gloit.hiperionida.helios.mapper.creation;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CuentaCorrienteClienteCreation extends AbsCuentaCorrienteCreation {
    private String clienteId;
    private String reciboId;
    private String facturaId;

    public CuentaCorrienteClienteCreation() {
    }

    public CuentaCorrienteClienteCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String monto, String notas, String tipoPago, String tipoMovimiento, String clienteId, String reciboId, String facturaId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada, monto, notas, tipoPago, tipoMovimiento);
        this.clienteId = clienteId;
        this.reciboId = reciboId;
        this.facturaId = facturaId;
    }
}