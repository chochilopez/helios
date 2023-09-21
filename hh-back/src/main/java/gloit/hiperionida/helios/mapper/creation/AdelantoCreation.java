package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;

@Getter
public class AdelantoCreation extends AbsAuditoriaCreation {
    private String id;
    private String descripcion;
    private String fecha;
    private String monto;
    private String notas;
    private String recibo;
    private String rendido;
    private String caja_id;
}
