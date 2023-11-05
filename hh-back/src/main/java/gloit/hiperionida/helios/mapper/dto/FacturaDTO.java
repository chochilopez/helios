package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
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
    private List<PagoDTO> pagos;
    private RemitoDTO remito;
    private ViajeFacturaDTO viaje;
    /*
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double descuento;
    private LocalDateTime fecha;
    private Double iva;
    private String numeroComprobante;
    private Double recarga;
    private Double subTotal;
    @Enumerated(EnumType.STRING)
    private TipoComprobanteEnum tipoComprobante;
    private Boolean pagada;

    private Long remito_id;
    private Long viaje_id;
     */
}
