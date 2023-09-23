package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ConciliacionBancoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String movimiento;
    private String fecha;
    private String concepto;
    private String monto;
    private BancoDTO banco;
}
