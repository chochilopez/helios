package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@MappedSuperclass
@Setter
@SuperBuilder
public abstract class AbsPersonaCreation extends AbsAuditoriaCreation {
    private String direccion;
    private String email;
    private String identificacion;
    private String nombre;
    private String notas;
    private String telefono;

    public AbsPersonaCreation() {
    }

    public AbsPersonaCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String direccion, String email, String identificacion, String nombre, String notas, String telefono) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.direccion = direccion;
        this.email = email;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.notas = notas;
        this.telefono = telefono;
    }
}
