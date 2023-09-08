package gloit.hiperionida.helios.util.mapper.creation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArchivoCreation extends AbsAuditoriaCreation {
    private String id;
    private String path;
    private String nombre;
    private String tipo;
    private String tamanio;
    private String descripcion;
}
