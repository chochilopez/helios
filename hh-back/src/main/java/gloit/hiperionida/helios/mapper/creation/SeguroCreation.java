package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;

@Getter
public class SeguroCreation extends AbsAuditoriaCreation {
    private String id;
    private String aseguradora_id;
    private String vencimiento_id;
}
