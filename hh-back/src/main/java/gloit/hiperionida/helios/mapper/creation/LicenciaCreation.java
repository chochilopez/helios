package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;

@Getter
public class LicenciaCreation extends AbsAuditoriaCreation {
    private String id;
    private String numero;
    private String categoria;
    private String vencimiento_id;
}
