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
    private String tara;
    private String kmCargado;
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

    /*
        private Double cantidadTransportada;
    private Double valorKm;
    private Double neto;
    private Double kmCargado;
    private Double kmVacio;
    @Column(columnDefinition = "TEXT")
    private String notas;

    private Long categoriaViaje_id;
    private Long origen_id;
    private Long carga_id;
    private Long destino_id;
    private Long vendedor_id;
    private Long intermediario_id;
    private Long comprador_id;
    private Long fecha_id;
     */
}

