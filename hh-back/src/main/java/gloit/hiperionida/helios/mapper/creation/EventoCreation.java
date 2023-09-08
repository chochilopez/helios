package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;

@Getter
public class EventoCreation extends AbsAuditoriaCreation {
    private String id;
    private String fecha;
    private String descripcion;
    private String habilitada;
    private String recordatorio;
    private String nombre;
}
