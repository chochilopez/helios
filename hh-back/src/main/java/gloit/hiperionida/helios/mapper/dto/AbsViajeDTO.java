package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@Setter
@SuperBuilder
public abstract class AbsViajeDTO extends AbsAuditoriaDTO {
    private String cantidadTransportada;
    private String fecha;
    private String kmCargado;
    private String notas;
    private String valorKm;

    private String categoriaViaje;
    private String cliente;
    private String destino;
    private String origen;

    private String fechaId;
    private String categoriaViajeId;
    private String clienteId;
    private String destinoId;
    private String origenId;

    public AbsViajeDTO() {
    }

    public AbsViajeDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String cantidadTransportada, String fecha, String kmCargado, String notas, String valorKm, String categoriaViaje, String cliente, String destino, String origen, String fechaId, String categoriaViajeId, String clienteId, String destinoId, String origenId) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.cantidadTransportada = cantidadTransportada;
        this.fecha = fecha;
        this.kmCargado = kmCargado;
        this.notas = notas;
        this.valorKm = valorKm;
        this.categoriaViaje = categoriaViaje;
        this.cliente = cliente;
        this.destino = destino;
        this.origen = origen;
        this.fechaId = fechaId;
        this.categoriaViajeId = categoriaViajeId;
        this.clienteId = clienteId;
        this.destinoId = destinoId;
        this.origenId = origenId;
    }
}
