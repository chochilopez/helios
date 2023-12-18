package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@MappedSuperclass
@Setter
@SuperBuilder
public abstract class AbsCuentaCorrienteCreation extends AbsAuditoriaCreation {
    private String monto;
    private String notas;
    private String tipoPago;
    private String tipoMovimiento;

    public AbsCuentaCorrienteCreation() {
    }

    public AbsCuentaCorrienteCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String monto, String notas, String tipoPago, String tipoMovimiento) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.monto = monto;
        this.notas = notas;
        this.tipoPago = tipoPago;
        this.tipoMovimiento = tipoMovimiento;
    }
}