package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class AdelantoDTO extends AbsAuditoriaDTO implements Serializable {
    private String fecha;
    private String monto;
    private String notas;
    private String rendido;
    private String caja;
    private String cajaId;
    private String conductor;
    private String conductorId;
    private String recibo;
    private String reciboId;

    public AdelantoDTO() {
    }

    public AdelantoDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String fecha, String monto, String notas, String rendido, String caja, String cajaId, String conductor, String conductorId, String recibo, String reciboId) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.fecha = fecha;
        this.monto = monto;
        this.notas = notas;
        this.rendido = rendido;
        this.caja = caja;
        this.cajaId = cajaId;
        this.conductor = conductor;
        this.conductorId = conductorId;
        this.recibo = recibo;
        this.reciboId = reciboId;
    }
}
