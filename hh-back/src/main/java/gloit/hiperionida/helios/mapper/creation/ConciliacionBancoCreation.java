package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.model.enums.MovimientoEnum;
import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ConciliacionBancoCreation extends AbsAuditoriaCreation {
    private String movimiento;
    private String fecha;
    private String concepto;
    private String monto;

    private String bancoId;
}
