package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ConciliacionBancoDTO extends AbsAuditoriaDTO implements Serializable {
    private String movimiento;
    private String fecha;
    private String concepto;
    private String monto;
    private String banco;

    public ConciliacionBancoDTO() {
    }

    public ConciliacionBancoDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String movimiento, String fecha, String concepto, String monto, String banco) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.movimiento = movimiento;
        this.fecha = fecha;
        this.concepto = concepto;
        this.monto = monto;
        this.banco = banco;
    }
}
