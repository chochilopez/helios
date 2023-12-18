package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ConciliacionBancoCreation extends AbsAuditoriaCreation {
    private String movimiento;
    private String fecha;
    private String concepto;
    private String monto;

    private String bancoId;

    public ConciliacionBancoCreation() {
    }

    public ConciliacionBancoCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String movimiento, String fecha, String concepto, String monto, String bancoId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.movimiento = movimiento;
        this.fecha = fecha;
        this.concepto = concepto;
        this.monto = monto;
        this.bancoId = bancoId;
    }
}
