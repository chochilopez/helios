package gloit.hiperionida.helios.util.model;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsAuditoriaModel {
    private LocalDateTime creada;
    private LocalDateTime eliminada;
    private LocalDateTime modificada;

    private Long creador_id;
    private Long eliminador_id;
    private Long modificador_id;
}
