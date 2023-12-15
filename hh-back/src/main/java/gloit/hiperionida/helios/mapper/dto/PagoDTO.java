package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PagoDTO extends AbsAuditoriaDTO implements Serializable {
    private String monto;
    private String notas;
    private String tipoPago;
    private String compraId;
    private String facturaId;
    private String recibo;
}
