package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CajaCreation extends AbsAuditoriaCreation {
    private String caja;
    private String notas;

    public CajaCreation() {
    }

    public CajaCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String caja, String notas) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.caja = caja;
        this.notas = notas;
    }
}
