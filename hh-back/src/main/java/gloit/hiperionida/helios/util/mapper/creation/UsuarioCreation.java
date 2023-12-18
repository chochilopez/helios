package gloit.hiperionida.helios.util.mapper.creation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class UsuarioCreation extends AbsAuditoriaCreation {
    private String nombre;
    private String dni;
    private String direccion;
    private String telefono;
    private String habilitada;
    private String username;
    private String password;
    private String token;
    private List<String> tokens_id;
    private List<String> roles_id;

    public UsuarioCreation() {
    }

    public UsuarioCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String nombre, String dni, String direccion, String telefono, String habilitada, String username, String password, String token, List<String> tokens_id, List<String> roles_id) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.habilitada = habilitada;
        this.username = username;
        this.password = password;
        this.token = token;
        this.tokens_id = tokens_id;
        this.roles_id = roles_id;
    }
}
