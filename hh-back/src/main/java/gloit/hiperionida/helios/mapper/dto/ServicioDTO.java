package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ServicioDTO extends AbsAuditoriaDTO implements Serializable {
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
