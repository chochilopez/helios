package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsViajeDTO extends AbsAuditoriaDTO {
    private String cantidad;
    private String importe;
    private String kgNeto;
    private String kmCargado;
    private String kmVacio;
    private String notas;
    private DireccionDTO carga;
    private CategoriaViajeDTO categoriaViaje;
    private ClienteDTO comprador;
    private DireccionDTO destino;
    private EventoDTO fecha;
    private DireccionDTO origen;
    private ClienteDTO vendedor;
}

