package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class RemitoDTO extends AbsAuditoriaDTO implements Serializable {
    private String descripcion;
    private String fecha;
    private String numero;

    public RemitoDTO() {
    }

    public RemitoDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String descripcion, String fecha, String numero) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.numero = numero;
    }
}
