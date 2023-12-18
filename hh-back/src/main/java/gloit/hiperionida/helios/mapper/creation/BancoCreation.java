package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class BancoCreation extends AbsAuditoriaCreation {
    private String banco;
    private String notas;

    public BancoCreation() {
    }

    public BancoCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String banco, String notas) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.banco = banco;
        this.notas = notas;
    }
}
