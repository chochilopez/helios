package gloit.hiperionida.helios.mapper.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CuentaCorrienteProveedorDTO extends AbsCuentaCorrienteDTO implements Serializable {
    private String proveedorId;
    private String proveedor;

    public CuentaCorrienteProveedorDTO() {
    }

    public CuentaCorrienteProveedorDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String monto, String notas, String tipoPago, String tipoMovimiento, String fecha, String saldo, String proveedorId, String proveedor) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId, monto, notas, tipoPago, tipoMovimiento, fecha, saldo);
        this.proveedorId = proveedorId;
        this.proveedor = proveedor;
    }
}