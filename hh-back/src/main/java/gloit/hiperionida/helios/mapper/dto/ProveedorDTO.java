package gloit.hiperionida.helios.mapper.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ProveedorDTO extends AbsPersonaDTO implements Serializable {
    public ProveedorDTO() {
    }

    public ProveedorDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String email, String identificacion, String nombre, String notas, String telefono, String direccion) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId, email, identificacion, nombre, notas, telefono, direccion);
    }
}
