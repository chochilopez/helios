package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class AdelantoCreation extends AbsAuditoriaCreation {
    private String fecha;
    private String monto;
    private String notas;
    private String rendido;
    private String cajaId;
    private String conductorId;
    private String reciboId;

    public AdelantoCreation() {
    }

    public AdelantoCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String fecha, String monto, String notas, String rendido, String cajaId, String conductorId, String reciboId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.fecha = fecha;
        this.monto = monto;
        this.notas = notas;
        this.rendido = rendido;
        this.cajaId = cajaId;
        this.conductorId = conductorId;
        this.reciboId = reciboId;
    }
}
