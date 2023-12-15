package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LicenciaDTO extends AbsAuditoriaDTO implements Serializable {
    private String categoria;
    private String conductor;
    private String conductorId;
    private String notas;
    private String numero;
    private String vencimiento;
    private String vencimientoId;
}
