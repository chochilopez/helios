package gloit.hiperionida.helios.util.mapper.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@Setter
@SuperBuilder
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

    public AbsAuditoriaDTO() {
    }

    public AbsAuditoriaDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId) {
        this.id = id;
        this.creada = creada;
        this.creador = creador;
        this.creadorId = creadorId;
        this.modificada = modificada;
        this.modificador = modificador;
        this.modificadorId = modificadorId;
        this.eliminada = eliminada;
        this.eliminador = eliminador;
        this.eliminadorId = eliminadorId;
    }
}
