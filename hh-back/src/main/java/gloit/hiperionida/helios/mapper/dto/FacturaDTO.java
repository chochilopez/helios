package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Setter
public class FacturaDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String descuento;
    private String fecha;
    private String iva;
    private String numeroComprobante;
    private String recarga;
    private String subTotal;
    private String tipoComprobante;
    private String pagada;
    private MovimientoDTO movimiento;
    private List<PagoDTO> pagos;
    private RemitoDTO remito;
    private ViajeDTO viaje;
}
