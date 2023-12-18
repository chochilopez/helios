package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.MovimientoEnum;
import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "cuenta_corriente_proveedor")
public class CuentaCorrienteProveedorModel extends AbsCuentaCorrienteModel {
    private Long proveedorId;

    public CuentaCorrienteProveedorModel() {
    }

    public CuentaCorrienteProveedorModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, Double monto, String notas, TipoPagoEnum tipoPago, MovimientoEnum tipoMovimiento, LocalDateTime fecha, Long proveedorId) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada, monto, notas, tipoPago, tipoMovimiento, fecha);
        this.proveedorId = proveedorId;
    }
}
