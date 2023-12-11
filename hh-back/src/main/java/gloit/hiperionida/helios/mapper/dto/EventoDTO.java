package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EventoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String descripcion;
    private String fecha;
    private String habilitada;
    private String nombre;
    private String recordatorioDias;
    private String recordatorioFecha;
}
