package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EventoCreation extends AbsAuditoriaCreation {
    private String id;
    private String fecha;
    private String descripcion;
    private String habilitada;
    private String recordatorio;
    private String nombre;
}
