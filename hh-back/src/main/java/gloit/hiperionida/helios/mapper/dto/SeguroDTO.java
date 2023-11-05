package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SeguroDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private ProveedorDTO aseguradora;
    private EventoDTO vencimiento;
    /*
        private Long id;

    private Long aseguradora_id;
    private Long vencimiento_id;
     */
}
