package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ConceptoCompraCreation extends AbsAuditoriaCreation {
    private String concepto;
    private String notas;

    public ConceptoCompraCreation() {
    }

    public ConceptoCompraCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String concepto, String notas) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.concepto = concepto;
        this.notas = notas;
    }
}
