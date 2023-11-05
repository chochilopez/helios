package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import gloit.hiperionida.helios.util.mapper.dto.ArchivoDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
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
    /*
        private Long id;
    private LocalDateTime fecha;
    private Double iva;
    private String numeroComprobante;
    private Double subTotal;
    @Enumerated(EnumType.STRING)
    private TipoComprobanteEnum tipoComprobante;
    @Column(columnDefinition = "TEXT")
    private String notas;

    private Long comprobante_id;
    private Long proveedor_id;
    private Long remito_id;
     */
}
