package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;

import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "banco")
public class BancoModel extends AbsAuditoriaModel {
    private String banco;
    @Column(columnDefinition = "TEXT")
    private String notas;

    public BancoModel() {
    }

    public BancoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String banco, String notas) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.banco = banco;
        this.notas = notas;
    }
}
