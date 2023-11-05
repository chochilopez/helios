package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EventoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String fecha;
    private String descripcion;
    private String habilitada;
    private String recordatorio;
    private String nombre;
    /*
        private Long id;
    private LocalDateTime fecha;
    private String descripcion;
    private Boolean habilitada;
    private Boolean recordatorio;
    private String nombre;
     */
}
