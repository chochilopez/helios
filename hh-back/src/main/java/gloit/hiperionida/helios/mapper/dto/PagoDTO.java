package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Setter;
import java.io.Serializable;

@Setter
public class PagoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String monto;
    private String notas;
    private String tipoPago;
    private ReciboDTO recibo;
}
