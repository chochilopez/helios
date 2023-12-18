package gloit.hiperionida.helios.mapper.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CuentaCorrienteClienteDTO extends AbsCuentaCorrienteDTO implements Serializable {
    private String clienteId;
    private String cliente;
    private String reciboId;
    private String recibo;
    private String facturaId;
    private String factura;

    public CuentaCorrienteClienteDTO() {
    }

    public CuentaCorrienteClienteDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String monto, String notas, String tipoPago, String tipoMovimiento, String fecha, String saldo, String clienteId, String cliente, String reciboId, String recibo, String facturaId, String factura) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId, monto, notas, tipoPago, tipoMovimiento, fecha, saldo);
        this.clienteId = clienteId;
        this.cliente = cliente;
        this.reciboId = reciboId;
        this.recibo = recibo;
        this.facturaId = facturaId;
        this.factura = factura;
    }
}