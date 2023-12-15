package gloit.hiperionida.helios.util.mapper.creation;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsAuditoriaCreation {
    private String id;
    private String creadorId;
    private String creada;
    private String eliminadorId;
    private String eliminada;
    private String modificadorId;
    private String modificada;
}
