package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.model.enums.MovimientoEnum;
import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ConciliacionBancoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String movimiento;
    private String fecha;
    private String concepto;
    private String monto;
    private String banco_id;
}
