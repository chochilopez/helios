package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Setter;
import java.io.Serializable;

@Setter
public class AdelantoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String descripcion;
    private String fecha;
    private String monto;
    private String notas;
    private String recibo;
    private String rendido;
    private CajaDTO caja;
    private ConductorDTO conductor;
}
