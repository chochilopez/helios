package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class SeguroCreation extends AbsAuditoriaCreation {
    private String notas;

    private String acopladoId;
    private String aseguradoraId;
    private String camionId;
    private String vencimiento;
    private String vencimientoId;

    public SeguroCreation() {
    }

    public SeguroCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String notas, String acopladoId, String aseguradoraId, String camionId, String vencimiento, String vencimientoId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.notas = notas;
        this.acopladoId = acopladoId;
        this.aseguradoraId = aseguradoraId;
        this.camionId = camionId;
        this.vencimiento = vencimiento;
        this.vencimientoId = vencimientoId;
    }
}
