package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;

@Getter
public class ServicioCreation extends AbsAuditoriaCreation {
    private String id;
    private String kmServicio;
    private String kmActual;
    private String kmProximo;
    private String tipo;
}
