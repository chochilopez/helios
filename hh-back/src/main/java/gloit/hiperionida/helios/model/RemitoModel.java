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
@Table(name = "remito")
public class RemitoModel extends AbsAuditoriaModel {
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private LocalDateTime fecha;
    private String numero;

    public RemitoModel() {
    }

    public RemitoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String descripcion, LocalDateTime fecha, String numero) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.numero = numero;
    }
}
