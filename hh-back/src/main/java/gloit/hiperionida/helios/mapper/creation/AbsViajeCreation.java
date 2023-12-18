package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@Setter
@SuperBuilder
public abstract class AbsViajeCreation extends AbsAuditoriaCreation {
    private String cantidadTransportada;
    private String kmCargado;
    private String valorKm;
    private String notas;
    private String fecha;

    private String categoriaViajeId;
    private String clienteId;
    private String fechaId;
    private String destinoId;
    private String origenId;

    public AbsViajeCreation() {
    }

    public AbsViajeCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String cantidadTransportada, String kmCargado, String valorKm, String notas, String fecha, String categoriaViajeId, String clienteId, String fechaId, String destinoId, String origenId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.cantidadTransportada = cantidadTransportada;
        this.kmCargado = kmCargado;
        this.valorKm = valorKm;
        this.notas = notas;
        this.fecha = fecha;
        this.categoriaViajeId = categoriaViajeId;
        this.clienteId = clienteId;
        this.fechaId = fechaId;
        this.destinoId = destinoId;
        this.origenId = origenId;
    }
}





