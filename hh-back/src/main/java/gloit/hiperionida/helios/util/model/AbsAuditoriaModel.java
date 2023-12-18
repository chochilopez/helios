package gloit.hiperionida.helios.util.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@Setter
@SuperBuilder
public abstract class AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long creadorId;
    private LocalDateTime creada;
    private Long modificadorId;
    private LocalDateTime modificada;
    private Long eliminadorId;
    private LocalDateTime eliminada;

    public AbsAuditoriaModel() {
    }

    public AbsAuditoriaModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada) {
        this.id = id;
        this.creadorId = creadorId;
        this.creada = creada;
        this.modificadorId = modificadorId;
        this.modificada = modificada;
        this.eliminadorId = eliminadorId;
        this.eliminada = eliminada;
    }
}