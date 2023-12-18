package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class LicenciaDTO extends AbsAuditoriaDTO implements Serializable {
    private String categoria;
    private String conductor;
    private String conductorId;
    private String notas;
    private String numero;
    private String vencimiento;
    private String vencimientoId;

    public LicenciaDTO() {
    }

    public LicenciaDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String categoria, String conductor, String conductorId, String notas, String numero, String vencimiento, String vencimientoId) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.categoria = categoria;
        this.conductor = conductor;
        this.conductorId = conductorId;
        this.notas = notas;
        this.numero = numero;
        this.vencimiento = vencimiento;
        this.vencimientoId = vencimientoId;
    }
}
