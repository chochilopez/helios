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
@Table(name = "licencia")
public class LicenciaModel extends AbsAuditoriaModel {
    private String numero;
    private String categoria;
    @Column(columnDefinition = "TEXT")
    private String notas;

    private Long conductorId;
    private Long vencimientoId;

    public LicenciaModel() {
    }

    public LicenciaModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String numero, String categoria, String notas, Long conductorId, Long vencimientoId) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.numero = numero;
        this.categoria = categoria;
        this.notas = notas;
        this.conductorId = conductorId;
        this.vencimientoId = vencimientoId;
    }
}
