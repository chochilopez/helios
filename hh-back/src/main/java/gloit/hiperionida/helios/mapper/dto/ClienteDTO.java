package gloit.hiperionida.helios.mapper.dto;

import lombok.Setter;
import java.io.Serializable;

@Setter
public class ClienteDTO extends AbsPersonaDTO implements Serializable {
    private String id;
}
