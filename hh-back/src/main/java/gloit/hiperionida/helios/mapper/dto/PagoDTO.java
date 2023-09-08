package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import gloit.hiperionida.helios.util.mapper.dto.ArchivoDTO;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Setter
public class PagoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String monto;
    private String notas;
    private String tipoPago;
    private List<ArchivoDTO> comprobante;
    private List<ChequeDTO> valores;
    private ReciboDTO recibo;
}
