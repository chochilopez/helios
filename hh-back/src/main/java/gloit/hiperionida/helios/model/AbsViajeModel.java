package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsViajeModel extends AbsAuditoriaModel {
    private Double cantidadTransportada;
    private Double valorKm;
    @Column(columnDefinition = "TEXT")
    private String notas;

    private Long categoriaViajeId;
    private Long compradorId;
    private Long destinoId;
    private Long fechaId;
    private Long origenId;
}

