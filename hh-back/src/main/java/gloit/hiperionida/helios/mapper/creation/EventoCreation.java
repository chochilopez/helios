package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EventoCreation extends AbsAuditoriaCreation {
    private String descripcion;
    private String fecha;
    private String habilitada;
    private String nombre;
    private String recordatorioDias;
    private String recordatorioFecha;
}
