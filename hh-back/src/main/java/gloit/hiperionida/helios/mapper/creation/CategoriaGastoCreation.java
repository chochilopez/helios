package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CategoriaGastoCreation extends AbsAuditoriaCreation {
    private String categoria;

    public CategoriaGastoCreation() {
    }

    public CategoriaGastoCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String categoria) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.categoria = categoria;
    }
}
