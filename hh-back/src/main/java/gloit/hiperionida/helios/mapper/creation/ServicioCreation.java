package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ServicioCreation extends AbsAuditoriaCreation {
    private String kmServicio;
    private String kmActual;
    private String kmProximo;
    private String tipo;

    private String camionId;

    public ServicioCreation() {
    }

    public ServicioCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String kmServicio, String kmActual, String kmProximo, String tipo, String camionId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.kmServicio = kmServicio;
        this.kmActual = kmActual;
        this.kmProximo = kmProximo;
        this.tipo = tipo;
        this.camionId = camionId;
    }
}
