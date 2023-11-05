package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ServicioCreation extends AbsAuditoriaCreation {
    private String id;
    private String kmServicio;
    private String kmActual;
    private String kmProximo;
    private String tipo;
        /*
        private Long id;
    private Double kmServicio;
    private Double kmActual;
    private Double kmProximo;
    private String tipo;

    private Long camion_id;
     */
}
