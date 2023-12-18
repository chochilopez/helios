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
@Table(name = "seguro")
public class SeguroModel extends AbsAuditoriaModel {
    private Long acopladoId;
    private Long aseguradoraId;
    private Long camionId;
    @Column(columnDefinition = "TEXT")
    private String notas;
    private Long vencimientoId;

    public SeguroModel() {
    }

    public SeguroModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String notas, Long aseguradoraId, Long vencimientoId, Long camionId, Long acopladoId) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.notas = notas;
        this.aseguradoraId = aseguradoraId;
        this.vencimientoId = vencimientoId;
        this.camionId = camionId;
        this.acopladoId = acopladoId;
    }
}
