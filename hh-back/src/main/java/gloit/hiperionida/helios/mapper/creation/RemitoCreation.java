package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;

@Getter
public class RemitoCreation extends AbsAuditoriaCreation {
    private String id;
    private String descripcion;
    private String fecha;
    private String numero;
}
