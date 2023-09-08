package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Setter;
import java.io.Serializable;

@Setter
public class ConciliacionBancoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String movimiento;
    private String fecha;
    private String concepto;
    private BancoDTO banco;
}
