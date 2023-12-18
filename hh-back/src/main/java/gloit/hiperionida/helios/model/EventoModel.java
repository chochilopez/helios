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
@Table(name = "evento")
public class EventoModel extends AbsAuditoriaModel {
    private LocalDateTime fecha;
    private Boolean habilitada;
    private String nombre;
    private String descripcion;
    private Integer recordatorioDias;
    private LocalDateTime recordatorioFecha;

    public EventoModel() {
    }

    public EventoModel(LocalDateTime fecha, Boolean habilitada, String nombre, String descripcion, Integer recordatorioDias, LocalDateTime recordatorioFecha, LocalDateTime creada, Long creadorId) {
        this.fecha = fecha;
        this.habilitada = habilitada;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recordatorioDias = recordatorioDias;
        this.recordatorioFecha = recordatorioFecha;
        this.setCreadorId(creadorId);
        this.setCreada(creada);
    }

    public EventoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, LocalDateTime fecha, Boolean habilitada, String nombre, String descripcion, Integer recordatorioDias, LocalDateTime recordatorioFecha) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.fecha = fecha;
        this.habilitada = habilitada;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recordatorioDias = recordatorioDias;
        this.recordatorioFecha = recordatorioFecha;
    }
}


