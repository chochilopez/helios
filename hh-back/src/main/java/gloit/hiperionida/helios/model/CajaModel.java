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
@Table(name = "caja")
public class CajaModel extends AbsAuditoriaModel {
    private String caja;
    @Column(columnDefinition = "TEXT")
    private String notas;

    public CajaModel() {
    }

    public CajaModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String caja, String notas) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.caja = caja;
        this.notas = notas;
    }
}
