package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;

@Getter
public class ConciliacionBancoCreation extends AbsAuditoriaCreation {
    private String id;
    private String movimiento;
    private String fecha;
    private String concepto;
    private String banco_id;
}
