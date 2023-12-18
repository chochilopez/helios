package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class RemitoCreation extends AbsAuditoriaCreation {
    private String descripcion;
    private String fecha;
    private String numero;

    public RemitoCreation() {
    }

    public RemitoCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String descripcion, String fecha, String numero) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.numero = numero;
    }
}
