package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class EventoCreation extends AbsAuditoriaCreation {
    private String descripcion;
    private String fecha;
    private String habilitada;
    private String nombre;
    private String recordatorioDias;
    private String recordatorioFecha;

    public EventoCreation() {
    }

    public EventoCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String descripcion, String fecha, String habilitada, String nombre, String recordatorioDias, String recordatorioFecha) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.habilitada = habilitada;
        this.nombre = nombre;
        this.recordatorioDias = recordatorioDias;
        this.recordatorioFecha = recordatorioFecha;
    }
}
