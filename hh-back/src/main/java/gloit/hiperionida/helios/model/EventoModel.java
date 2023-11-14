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
    private String descripcion;
    private Boolean habilitada;
    private Boolean recordatorio;
    private String nombre;

    public EventoModel(LocalDateTime fecha, String descripcion, Boolean habilitada, Boolean recordatorio, String nombre, LocalDateTime creada, Long creadorId) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.habilitada = habilitada;
        this.recordatorio = recordatorio;
        this.nombre = nombre;
        this.setCreada(creada);
        this.setCreadorId(creadorId);
    }
}


