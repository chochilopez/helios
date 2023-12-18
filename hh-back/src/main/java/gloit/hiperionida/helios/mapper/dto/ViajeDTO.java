package gloit.hiperionida.helios.mapper.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ViajeDTO extends AbsViajeDTO implements Serializable {
    private String guia;
    private String kmVacio;
    private String neto;

    private String acoplado;
    private String acopladoId;
    private String camion;
    private String camionId;
    private String carga;
    private String cargaId;
    private String conductor;
    private String conductorId;
    private String intermediario;
    private String intermediarioId;
    private String vendedor;
    private String vendedorId;

    private String tipoComprobante;
    private String numeroComprobante;
    private String pagada;
    private String vencida;

    public ViajeDTO() {
    }

    public ViajeDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String cantidadTransportada, String fecha, String kmCargado, String notas, String valorKm, String categoriaViaje, String cliente, String destino, String origen, String fechaId, String categoriaViajeId, String clienteId, String destinoId, String origenId, String guia, String kmVacio, String neto, String acoplado, String acopladoId, String camion, String camionId, String carga, String cargaId, String conductor, String conductorId, String intermediario, String intermediarioId, String vendedor, String vendedorId, String tipoComprobante, String numeroComprobante, String pagada, String vencida) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId, cantidadTransportada, fecha, kmCargado, notas, valorKm, categoriaViaje, cliente, destino, origen, fechaId, categoriaViajeId, clienteId, destinoId, origenId);
        this.guia = guia;
        this.kmVacio = kmVacio;
        this.neto = neto;
        this.acoplado = acoplado;
        this.acopladoId = acopladoId;
        this.camion = camion;
        this.camionId = camionId;
        this.carga = carga;
        this.cargaId = cargaId;
        this.conductor = conductor;
        this.conductorId = conductorId;
        this.intermediario = intermediario;
        this.intermediarioId = intermediarioId;
        this.vendedor = vendedor;
        this.vendedorId = vendedorId;
        this.tipoComprobante = tipoComprobante;
        this.numeroComprobante = numeroComprobante;
        this.pagada = pagada;
        this.vencida = vencida;
    }
}

