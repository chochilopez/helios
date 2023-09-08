package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Setter;
import java.io.Serializable;

@Setter
public class ServicioDTO extends AbsVehiculoDTO implements Serializable {
    private String id;
    private String kmServicio;
    private String kmActual;
    private String kmProximo;
    private String tipo;
    private CamionDTO camion;
}
