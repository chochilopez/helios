package gloit.hiperionida.helios.mapper.creation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ViajeCreation extends AbsViajeCreation {
    private String id;
    private String guia;
    private String kmVacio;
    private String neto;

    private String acopladoId;
    private String camionId;
    private String cargaId;
    private String conductorId;
    private String intermediarioId;
    private String vendedorId;
}
/*
    this.acopladoId = ''
    this.camionId = ''
    this.cantidadTransportada = ''
    this.cargaId = ''
    this.categoriaViajeId = ''
    this.clienteId = ''
    this.conductorId = ''
    this.destinoId = ''
    this.fecha = ''
    this.fechaId = ''
    this.guia = ''
    this.intermediarioId = ''
    this.kmCargado = ''
    this.kmVacio = ''
    this.neto = ''
    this.notas = ''
    this.origenId = ''
    this.valorKm = ''
    this.vendedorId = ''
 */