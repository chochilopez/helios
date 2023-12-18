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
@Table(name = "servicio")
public class ServicioModel extends AbsAuditoriaModel {
    private Double kmServicio;
    private Double kmActual;
    private Double kmProximo;
    private String tipo;
    @Column(columnDefinition = "TEXT")
    private String notas;

    private Long camionId;

    public ServicioModel() {
    }

    public ServicioModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, Double kmServicio, Double kmActual, Double kmProximo, String tipo, String notas, Long camionId) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.kmServicio = kmServicio;
        this.kmActual = kmActual;
        this.kmProximo = kmProximo;
        this.tipo = tipo;
        this.notas = notas;
        this.camionId = camionId;
    }
}
