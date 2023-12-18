package gloit.hiperionida.helios.mapper.creation;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CuentaCorrienteProveedorCreation extends AbsCuentaCorrienteCreation {
    private String proveedorId;

    public CuentaCorrienteProveedorCreation() {
    }

    public CuentaCorrienteProveedorCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String monto, String notas, String tipoPago, String tipoMovimiento, String proveedorId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada, monto, notas, tipoPago, tipoMovimiento);
        this.proveedorId = proveedorId;
    }
}
