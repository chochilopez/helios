package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Setter;
import java.io.Serializable;

@Setter
public class EventoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String fecha;
    private String descripcion;
    private String habilitada;
    private String recordatorio;
    private String nombre;
}
