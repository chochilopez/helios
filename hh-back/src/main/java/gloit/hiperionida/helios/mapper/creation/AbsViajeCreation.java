package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsViajeCreation extends AbsAuditoriaCreation {
    private String cantidadTransportada;
    private String valorKm;
    private String notas;

    private String categoriaViajeId;
    private String compradorId;
    private String destinoId;
    private String fechaId;
    private String origenId;

}

