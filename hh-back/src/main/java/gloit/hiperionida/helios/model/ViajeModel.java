package gloit.hiperionida.helios.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "viaje")
public class ViajeModel extends AbsViajeModel {
    private String guia;
    private Double kmVacio;
    private Double neto;
    private Long acopladoId;
    private Long camionId;
    private Long cargaId;
    private Long conductorId;
    private Long intermediarioId;
    private Long vendedorId;

    public ViajeModel() {
    }

    public ViajeModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, Integer cantidadTransportada, Double kmCargado, Double valorKm, String notas, Long categoriaViajeId, Long clienteId, Long destinoId, Long fechaId, Long origenId, String guia, Double kmVacio, Double neto, Long acopladoId, Long camionId, Long cargaId, Long conductorId, Long intermediarioId, Long vendedorId) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada, cantidadTransportada, kmCargado, valorKm, notas, categoriaViajeId, clienteId, destinoId, fechaId, origenId);
        this.guia = guia;
        this.kmVacio = kmVacio;
        this.neto = neto;
        this.acopladoId = acopladoId;
        this.camionId = camionId;
        this.cargaId = cargaId;
        this.conductorId = conductorId;
        this.intermediarioId = intermediarioId;
        this.vendedorId = vendedorId;
    }
}

