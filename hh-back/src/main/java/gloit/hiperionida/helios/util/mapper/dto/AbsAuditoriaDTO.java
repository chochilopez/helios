package gloit.hiperionida.helios.util.mapper.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsAuditoriaDTO {
    private String id;
    private String creada;
    private String creador;
    private String creadorId;
    private String modificada;
    private String modificador;
    private String modificadorId;
    private String eliminada;
    private String eliminador;
    private String eliminadorId;
}
