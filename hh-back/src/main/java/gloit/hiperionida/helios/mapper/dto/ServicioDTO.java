package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ServicioDTO extends AbsAuditoriaDTO implements Serializable {
    private String kmServicio;
    private String kmActual;
    private String kmProximo;
    private String tipo;
    private String camionId;

    public ServicioDTO() {
    }

    public ServicioDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String kmServicio, String kmActual, String kmProximo, String tipo, String camionId) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.kmServicio = kmServicio;
        this.kmActual = kmActual;
        this.kmProximo = kmProximo;
        this.tipo = tipo;
        this.camionId = camionId;
    }
}
