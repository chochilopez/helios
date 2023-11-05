package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsPersonaModel extends AbsAuditoriaModel {
    private String email;
    private String identificacion;
    private String nombre;
    @Column(columnDefinition = "TEXT")
    private String notas;
    private String telefono;

    private Long direccion_id;
}
