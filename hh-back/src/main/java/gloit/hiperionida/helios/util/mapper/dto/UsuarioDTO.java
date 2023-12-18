package gloit.hiperionida.helios.util.mapper.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class UsuarioDTO extends AbsAuditoriaDTO implements Serializable {
    private String nombre;
    private String dni;
    private String direccion;
    private String telefono;
    private String username;
    private List<String> roles = new ArrayList<>();

    public UsuarioDTO() {
    }

    public UsuarioDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String nombre, String dni, String direccion, String telefono, String username, List<String> roles) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.username = username;
        this.roles = roles;
    }
}
