package gloit.hiperionida.helios.mapper.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PresupuestoDTO extends AbsViajeDTO implements Serializable {
    private String id;
    private String validez;
    /*
        private Long id;
    private LocalDateTime validez;
     */
}
