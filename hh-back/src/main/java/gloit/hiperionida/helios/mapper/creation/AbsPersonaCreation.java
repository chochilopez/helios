package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsPersonaCreation extends AbsAuditoriaCreation {
    private String direccion;
    private String email;
    private String identificacion;
    private String nombre;
    private String notas;
    private String telefono;
}
