package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class DireccionCreation extends AbsAuditoriaCreation {
    private String ciudad;
    private String direccion;
    private String nombre;
    private String notas;

    public DireccionCreation() {
    }

    public DireccionCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String ciudad, String direccion, String nombre, String notas) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.nombre = nombre;
        this.notas = notas;
    }
}
