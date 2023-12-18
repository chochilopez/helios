package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class GastoDTO extends AbsAuditoriaDTO implements Serializable {
    private String monto;
    private String notas;
    private String categoriaGasto;
    private String viajeId;

    public GastoDTO() {
    }

    public GastoDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String monto, String notas, String categoriaGasto, String viajeId) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.monto = monto;
        this.notas = notas;
        this.categoriaGasto = categoriaGasto;
        this.viajeId = viajeId;
    }
}
