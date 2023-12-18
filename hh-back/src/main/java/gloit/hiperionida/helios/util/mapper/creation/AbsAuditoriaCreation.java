package gloit.hiperionida.helios.util.mapper.creation;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@Setter
@SuperBuilder
public abstract class AbsAuditoriaCreation {
    private String id;
    private String creadorId;
    private String creada;
    private String eliminadorId;
    private String eliminada;
    private String modificadorId;
    private String modificada;

    public AbsAuditoriaCreation() {
    }

    public AbsAuditoriaCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada) {
        this.id = id;
        this.creadorId = creadorId;
        this.creada = creada;
        this.eliminadorId = eliminadorId;
        this.eliminada = eliminada;
        this.modificadorId = modificadorId;
        this.modificada = modificada;
    }
}
