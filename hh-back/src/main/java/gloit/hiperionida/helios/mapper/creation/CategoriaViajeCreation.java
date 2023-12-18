package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CategoriaViajeCreation extends AbsAuditoriaCreation {
    private String categoria;
    private String notas;

    public CategoriaViajeCreation() {
    }

    public CategoriaViajeCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String categoria, String notas) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.categoria = categoria;
        this.notas = notas;
    }
}
