package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsViajeDTO extends AbsAuditoriaDTO {
    private String cantidadTransportada;
    private String fecha;
    private String kmCargado;
    private String notas;
    private String valorKm;

    private String categoriaViaje;
    private String comprador;
    private String destino;
    private String origen;

    private Long categoriaViajeId;
    private Long compradorId;
    private Long destinoId;
    private Long origenId;
}
