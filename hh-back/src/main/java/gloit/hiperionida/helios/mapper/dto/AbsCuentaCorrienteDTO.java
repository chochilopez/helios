package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@MappedSuperclass
@Setter
@SuperBuilder
public abstract class AbsCuentaCorrienteDTO extends AbsAuditoriaDTO implements Serializable {
    private String monto;
    private String notas;
    private String tipoPago;
    private String tipoMovimiento;
    private String fecha;
    private String saldo;

    public AbsCuentaCorrienteDTO() {
    }

    public AbsCuentaCorrienteDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String monto, String notas, String tipoPago, String tipoMovimiento, String fecha, String saldo) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.monto = monto;
        this.notas = notas;
        this.tipoPago = tipoPago;
        this.tipoMovimiento = tipoMovimiento;
        this.fecha = fecha;
        this.saldo = saldo;
    }
}