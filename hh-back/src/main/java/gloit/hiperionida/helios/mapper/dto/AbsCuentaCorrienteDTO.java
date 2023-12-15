package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.io.Serializable;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsCuentaCorrienteDTO extends AbsAuditoriaDTO implements Serializable {
    private String monto;
    private String notas;
    private String tipoPago;
    private String tipoMovimiento;
    private String fecha;
    private String saldo;
}