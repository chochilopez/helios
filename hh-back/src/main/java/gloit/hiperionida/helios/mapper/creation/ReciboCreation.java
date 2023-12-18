package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ReciboCreation extends AbsAuditoriaCreation {
    private String monto;
    private String fecha;

    public ReciboCreation() {
    }

    public ReciboCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String monto, String fecha) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.monto = monto;
        this.fecha = fecha;
    }
}
