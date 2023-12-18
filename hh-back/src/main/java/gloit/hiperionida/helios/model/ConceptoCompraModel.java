package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "concepto_compra")
public class ConceptoCompraModel extends AbsAuditoriaModel {
    private String concepto;
    @Column(columnDefinition = "TEXT")
    private String notas;

    public ConceptoCompraModel() {
    }

    public ConceptoCompraModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String concepto, String notas) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.concepto = concepto;
        this.notas = notas;
    }
}
