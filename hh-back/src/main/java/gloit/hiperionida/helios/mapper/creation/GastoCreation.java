package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class GastoCreation extends AbsAuditoriaCreation {
    private String monto;
    private String notas;

    private String categoriaGastoId;
    private String viajeId;

    public GastoCreation() {
    }

    public GastoCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String monto, String notas, String categoriaGastoId, String viajeId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.monto = monto;
        this.notas = notas;
        this.categoriaGastoId = categoriaGastoId;
        this.viajeId = viajeId;
    }
}
