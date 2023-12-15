package gloit.hiperionida.helios.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CuentaCorrienteClienteDTO extends AbsCuentaCorrienteDTO implements Serializable {
    private String clienteId;
    private String cliente;
    private String reciboId;
    private String recibo;
    private String facturaId;
    private String factura;
}