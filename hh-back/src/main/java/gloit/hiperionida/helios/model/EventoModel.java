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
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private String nombre;
    private String descripcion;
    private Boolean recordatorio;

    public EventoModel(LocalDateTime fecha, LocalDateTime inicio, LocalDateTime fin, String nombre, String descripcion, Boolean recordatorio, LocalDateTime creada, Long creadorId) {
        this.fecha = fecha;
        this.inicio = inicio;
        this.fin = fin;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recordatorio = recordatorio;
        this.setCreada(creada);
        this.setCreadorId(creadorId);
    }
}


