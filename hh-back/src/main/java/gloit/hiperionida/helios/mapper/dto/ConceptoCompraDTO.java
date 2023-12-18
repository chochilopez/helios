package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ConceptoCompraDTO extends AbsAuditoriaDTO implements Serializable {
    private String concepto;
    private String notas;

    public ConceptoCompraDTO() {
    }

    public ConceptoCompraDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String concepto, String notas) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.concepto = concepto;
        this.notas = notas;
    }
}
