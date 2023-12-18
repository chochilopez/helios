package gloit.hiperionida.helios.mapper.creation;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ClienteCreation extends AbsPersonaCreation {
    public ClienteCreation() {
    }

    public ClienteCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String direccion, String email, String identificacion, String nombre, String notas, String telefono) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada, direccion, email, identificacion, nombre, notas, telefono);
    }
}
