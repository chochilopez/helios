package gloit.hiperionida.helios.util.mapper.creation;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ArchivoCreation extends AbsAuditoriaCreation {
    private String descripcion;
    private String nombre;
    private String path;
    private String tamanio;
    private String tipo;

    public ArchivoCreation() {
    }

    public ArchivoCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String descripcion, String nombre, String path, String tamanio, String tipo) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.path = path;
        this.tamanio = tamanio;
        this.tipo = tipo;
    }
}
