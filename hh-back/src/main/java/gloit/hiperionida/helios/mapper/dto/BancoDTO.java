package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Setter;

import java.io.Serializable;

@Setter
public class BancoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String banco;
    private String notas;
}
