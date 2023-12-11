package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "evento")
public class EventoModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    private Boolean habilitada;
    private String nombre;
    private String descripcion;
    private Integer recordatorioDias;
    private LocalDateTime recordatorioFecha;

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
}


