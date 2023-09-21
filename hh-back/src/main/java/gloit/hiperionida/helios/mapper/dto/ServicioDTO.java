package gloit.hiperionida.helios.mapper.dto;

import lombok.Setter;
import java.io.Serializable;

@Setter
public class ServicioDTO extends AbsVehiculoDTO implements Serializable {
    private String id;
    private String kmServicio;
    private String kmActual;
    private String kmProximo;
    private String tipo;
}
