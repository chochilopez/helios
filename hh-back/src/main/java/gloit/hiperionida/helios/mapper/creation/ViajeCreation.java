package gloit.hiperionida.helios.mapper.creation;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ViajeCreation extends AbsViajeCreation {
    private String guia;
    private String kmVacio;
    private String neto;

    private String acopladoId;
    private String camionId;
    private String cargaId;
    private String conductorId;
    private String intermediarioId;
    private String vendedorId;

    public ViajeCreation() {
    }

    public ViajeCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String cantidadTransportada, String kmCargado, String valorKm, String notas, String fecha, String categoriaViajeId, String clienteId, String fechaId, String destinoId, String origenId, String guia, String kmVacio, String neto, String acopladoId, String camionId, String cargaId, String conductorId, String intermediarioId, String vendedorId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada, cantidadTransportada, kmCargado, valorKm, notas, fecha, categoriaViajeId, clienteId, fechaId, destinoId, origenId);
        this.guia = guia;
        this.kmVacio = kmVacio;
        this.neto = neto;
        this.acopladoId = acopladoId;
        this.camionId = camionId;
        this.cargaId = cargaId;
        this.conductorId = conductorId;
        this.intermediarioId = intermediarioId;
        this.vendedorId = vendedorId;
    }
}
