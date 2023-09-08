package gloit.hiperionida.helios.util.mapper.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsAuditoriaDTO {
    private UsuarioDTO creador;
    private String creada;
    private UsuarioDTO modificador;
    private String modificada;
    private UsuarioDTO eliminador;
    private String eliminada;
}
