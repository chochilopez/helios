package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsViajeDTO extends AbsAuditoriaDTO {
    private String cantidadTransportada;
    private String valorKm;
    private String neto;
    private String tara;    private String kmCargado;
    private String kmVacio;
    private String notas;
    private CategoriaViajeDTO categoriaViaje;
    private DireccionDTO origen;
    private DireccionDTO carga;
    private DireccionDTO destino;
    private ClienteDTO vendedor;
    private ClienteDTO intermediario;
    private ClienteDTO comprador;
    private EventoDTO fecha;
}

