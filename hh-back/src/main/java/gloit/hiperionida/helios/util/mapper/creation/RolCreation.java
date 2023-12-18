package gloit.hiperionida.helios.util.mapper.creation;

import lombok.Data;

@Data
public class RolCreation {
    private String id;
    private String rol;

    public RolCreation(String rol) {
        this.rol = rol;
    }
}
