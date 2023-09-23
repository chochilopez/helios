package gloit.hiperionida.helios.util.mapper.creation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RolCreation {
    private String id;
    @NotNull(message = "El campo rol no puede estar vacio.")
    @Size(min=4, max=40, message = "El campo rol debe tener entre 4 y 40 caracteres.")
    private String rol;

    public RolCreation(String rol) {
        this.rol = rol;
    }
}
