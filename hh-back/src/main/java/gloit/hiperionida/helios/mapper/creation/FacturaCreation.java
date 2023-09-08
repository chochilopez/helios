package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;
import java.util.List;

@Getter
public class FacturaCreation extends AbsAuditoriaCreation {
    private String id;
    private String descuento;
    private String fecha;
    private String iva;
    private String numeroComprobante;
    private String recarga;
    private String subTotal;
    private String tipoComprobante;
    private String pagada;
    private String acoplado_id;
    private String camion_id;
    private String cliente_id;
    private String conductor_id;
    private String destino_id;
    private String movimiento_id;
    private String origen_id;
    private List<String> pagos_id;
    private String remito_id;
    private String viaje_id;
}
