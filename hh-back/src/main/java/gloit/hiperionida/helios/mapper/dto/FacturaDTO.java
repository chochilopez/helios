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
    private AcopladoDTO acoplado;
    private CamionDTO camion;
    private ClienteDTO cliente;
    private ConductorDTO conductor;
    private DireccionDTO destino;
    private MovimientoDTO movimiento;
    private DireccionDTO origen;
    private List<PagoDTO> pagos;
    private RemitoDTO remito;
    private ViajeDTO viaje;
}
