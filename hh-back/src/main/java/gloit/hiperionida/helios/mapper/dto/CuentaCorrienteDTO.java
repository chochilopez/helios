package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CuentaCorrienteDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String monto;
    private String notas;
    private String tipoPago;
    private String tipoMovimiento;
    private String fecha;

    private String clienteId;
    private String cliente;
    private String reciboId;
    private String recibo;
    private String facturaId;
    private String factura;
}