package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SeguroDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String notas;

    private String acoplado;
    private String aseguradora;
    private String camion;
    private String vencimiento;
}
