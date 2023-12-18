package gloit.hiperionida.helios.util.mapper.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ArchivoDTO extends AbsAuditoriaDTO implements Serializable {
    private String descripcion;
    private String nombre;
    private String path;
    private String tamanio;
    private String tipo;

    public ArchivoDTO() {
    }

    public ArchivoDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String descripcion, String nombre, String path, String tamanio, String tipo) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.path = path;
        this.tamanio = tamanio;
        this.tipo = tipo;
    }
}