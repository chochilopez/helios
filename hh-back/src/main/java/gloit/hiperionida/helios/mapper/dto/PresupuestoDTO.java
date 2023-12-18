package gloit.hiperionida.helios.mapper.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class PresupuestoDTO extends AbsViajeDTO implements Serializable {
    private String validez;
    private String fechaEmision;

    public PresupuestoDTO() {
    }

    public PresupuestoDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String cantidadTransportada, String fecha, String kmCargado, String notas, String valorKm, String categoriaViaje, String cliente, String destino, String origen, String fechaId, String categoriaViajeId, String clienteId, String destinoId, String origenId, String validez, String fechaEmision) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId, cantidadTransportada, fecha, kmCargado, notas, valorKm, categoriaViaje, cliente, destino, origen, fechaId, categoriaViajeId, clienteId, destinoId, origenId);
        this.validez = validez;
        this.fechaEmision = fechaEmision;
    }
}