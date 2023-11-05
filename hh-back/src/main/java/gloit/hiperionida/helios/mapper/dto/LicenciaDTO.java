package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LicenciaDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String numero;
    private String categoria;
    private EventoDTO vencimiento;
    /*
        private Long id;
    private String numero;
    private String categoria;

    private Long vencimiento_id;
     */
}
