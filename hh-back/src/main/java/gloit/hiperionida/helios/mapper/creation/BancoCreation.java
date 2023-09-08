package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;

@Getter
public class BancoCreation extends AbsAuditoriaCreation {
    private String id;
    private String banco;
    private String notas;
}
