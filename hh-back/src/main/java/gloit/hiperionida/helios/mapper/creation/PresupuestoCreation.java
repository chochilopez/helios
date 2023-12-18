package gloit.hiperionida.helios.mapper.creation;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class PresupuestoCreation extends AbsViajeCreation {
    private String validez;

    public PresupuestoCreation() {
    }

    public PresupuestoCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String cantidadTransportada, String kmCargado, String valorKm, String notas, String fecha, String categoriaViajeId, String clienteId, String fechaId, String destinoId, String origenId, String validez) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada, cantidadTransportada, kmCargado, valorKm, notas, fecha, categoriaViajeId, clienteId, fechaId, destinoId, origenId);
        this.validez = validez;
    }
}
