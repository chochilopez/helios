package gloit.hiperionida.helios.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "presupuesto")
public class PresupuestoModel extends AbsViajeModel{
    private Integer validez;
    @Column(columnDefinition = "TEXT")
    private String notas;

    public PresupuestoModel() {
    }

    public PresupuestoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, Integer cantidadTransportada, Double kmCargado, Double valorKm, String notas, Long categoriaViajeId, Long clienteId, Long destinoId, Long fechaId, Long origenId, Integer validez, String notas1) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada, cantidadTransportada, kmCargado, valorKm, notas, categoriaViajeId, clienteId, destinoId, fechaId, origenId);
        this.validez = validez;
        this.notas = notas1;
    }
}
