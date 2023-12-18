package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class PlanCuentaCreation extends AbsAuditoriaCreation {
    private String nombre;

    public PlanCuentaCreation() {
    }

    public PlanCuentaCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String nombre) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.nombre = nombre;
    }
}
