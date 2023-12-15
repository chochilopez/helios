package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsCuentaCorrienteCreation extends AbsAuditoriaCreation {
    private String monto;
    private String notas;
    private String tipoPago;
    private String tipoMovimiento;
}