package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EventoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String fecha;
    private String inicio;
    private String fin;
    private String descripcion;
    private String nombre;
    private String recordatorio;
}
