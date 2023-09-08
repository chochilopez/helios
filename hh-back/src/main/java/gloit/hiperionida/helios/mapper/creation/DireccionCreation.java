package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;

@Getter
public class DireccionCreation extends AbsAuditoriaCreation {
    private String id;
    private String direccion;
    private String nombre;
    private String ubicacion_id;
}
