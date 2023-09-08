package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import gloit.hiperionida.helios.util.mapper.dto.ArchivoDTO;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Setter
public class CompraDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String fecha;
    private String iva;
    private String numeroComprobante;
    private String subTotal;
    private String tipoComprobante;
    private String notas;
    private ArchivoDTO comprobante;
    private List<PagoDTO> pagos;
    private ProveedorDTO proveedor;
    private RemitoDTO remito;
}
