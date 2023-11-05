package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RemitoCreation extends AbsAuditoriaCreation {
    private String id;
    private String descripcion;
    private String fecha;
    private String numero;
        /*
        private Long id;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private LocalDateTime fecha;
    private String numero;
     */
}
