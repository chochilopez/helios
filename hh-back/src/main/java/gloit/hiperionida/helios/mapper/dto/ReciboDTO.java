package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReciboDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String monto;
    private String fecha;
    /*
        private Long id;
    private Double monto;
    private LocalDateTime fecha;
     */
}
