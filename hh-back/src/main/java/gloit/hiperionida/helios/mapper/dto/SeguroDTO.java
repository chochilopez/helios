package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class SeguroDTO extends AbsAuditoriaDTO implements Serializable {
    private String notas;

    private String acoplado;
    private String acopladoId;
    private String aseguradora;
    private String aseguradoraId;
    private String camion;
    private String camionId;
    private String vencimiento;
    private String vencimientoId;

    public SeguroDTO() {
    }

    public SeguroDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String notas, String acoplado, String acopladoId, String aseguradora, String aseguradoraId, String camion, String camionId, String vencimiento, String vencimientoId) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.notas = notas;
        this.acoplado = acoplado;
        this.acopladoId = acopladoId;
        this.aseguradora = aseguradora;
        this.aseguradoraId = aseguradoraId;
        this.camion = camion;
        this.camionId = camionId;
        this.vencimiento = vencimiento;
        this.vencimientoId = vencimientoId;
    }
}
