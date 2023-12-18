package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@Setter
@SuperBuilder
public abstract class AbsViajeModel extends AbsAuditoriaModel {
    private Integer cantidadTransportada;
    private Double kmCargado;
    private Double valorKm;
    @Column(columnDefinition = "TEXT")
    private String notas;

    private Long categoriaViajeId;
    private Long clienteId;
    private Long destinoId;
    private Long fechaId;
    private Long origenId;

    public AbsViajeModel() {
    }

    public AbsViajeModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, Integer cantidadTransportada, Double kmCargado, Double valorKm, String notas, Long categoriaViajeId, Long clienteId, Long destinoId, Long fechaId, Long origenId) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.cantidadTransportada = cantidadTransportada;
        this.kmCargado = kmCargado;
        this.valorKm = valorKm;
        this.notas = notas;
        this.categoriaViajeId = categoriaViajeId;
        this.clienteId = clienteId;
        this.destinoId = destinoId;
        this.fechaId = fechaId;
        this.origenId = origenId;
    }
}

