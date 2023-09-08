package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Setter;

import java.io.Serializable;

@Setter
public class LicenciaDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String numero;
    private String categoria;
    private EventoDTO vencimiento;
}
