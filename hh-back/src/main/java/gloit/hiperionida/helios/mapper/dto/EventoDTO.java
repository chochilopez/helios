package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class EventoDTO extends AbsAuditoriaDTO implements Serializable {
    private String descripcion;
    private String fecha;
    private String habilitada;
    private String nombre;
    private String recordatorioDias;
    private String recordatorioFecha;

    public EventoDTO() {
    }

    public EventoDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String descripcion, String fecha, String habilitada, String nombre, String recordatorioDias, String recordatorioFecha) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.habilitada = habilitada;
        this.nombre = nombre;
        this.recordatorioDias = recordatorioDias;
        this.recordatorioFecha = recordatorioFecha;
    }
}
