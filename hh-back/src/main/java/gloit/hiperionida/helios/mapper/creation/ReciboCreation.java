package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReciboCreation extends AbsAuditoriaCreation {
    private String id;
    private String monto;
    private String fecha;
        /*
        private Long id;
    private Double monto;
    private LocalDateTime fecha;
     */
}
