package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class LicenciaCreation extends AbsAuditoriaCreation {
    private String numero;
    private String categoria;
    private String notas;
    private String conductorId;
    private String vencimiento;
    private String vencimientoId;

    public LicenciaCreation() {
    }

    public LicenciaCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String numero, String categoria, String notas, String conductorId, String vencimiento, String vencimientoId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.numero = numero;
        this.categoria = categoria;
        this.notas = notas;
        this.conductorId = conductorId;
        this.vencimiento = vencimiento;
        this.vencimientoId = vencimientoId;
    }
}
