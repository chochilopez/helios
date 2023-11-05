package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsPersonaDTO extends AbsAuditoriaDTO {
    private String email;
    private String identificacion;
    private String nombre;
    private String notas;
    private String telefono;
    private String direccion_id;
}
