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
@Table(name = "adelanto")
public class AdelantoModel extends AbsAuditoriaModel {
    private Long id;
    private LocalDateTime fecha;
    private Double monto;
    @Column(columnDefinition = "TEXT")
    private String notas;
    private Boolean rendido;

    private Long cajaId;
    private Long conductorId;
    private Long reciboId;

    public AdelantoModel() {
    }

    public AdelantoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, Long id1, LocalDateTime fecha, Double monto, String notas, Boolean rendido, Long cajaId, Long conductorId, Long reciboId) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.id = id1;
        this.fecha = fecha;
        this.monto = monto;
        this.notas = notas;
        this.rendido = rendido;
        this.cajaId = cajaId;
        this.conductorId = conductorId;
        this.reciboId = reciboId;
    }
}
