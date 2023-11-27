package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EventoCreation extends AbsAuditoriaCreation {
    private String id;
    private String inicio;
    private String fin;
    private String descripcion;
    private String recordatorio;
    private String nombre;
}
