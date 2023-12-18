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
@Table(name = "categoria_gasto")
public class CategoriaGastoModel extends AbsAuditoriaModel {
    private String categoria;
    @Column(columnDefinition = "TEXT")
    private String notas;

    public CategoriaGastoModel() {
    }

    public CategoriaGastoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String categoria, String notas) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.categoria = categoria;
        this.notas = notas;
    }
}

