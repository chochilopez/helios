package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Setter;

import java.io.Serializable;

@Setter
public class ClienteDTO extends AbsPersonaDTO implements Serializable {
    private String id;
}
