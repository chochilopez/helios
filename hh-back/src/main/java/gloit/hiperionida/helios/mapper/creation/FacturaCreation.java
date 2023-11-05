package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
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
    private String movimiento_id;
    private List<String> pagos_id;
    private String remito_id;
    private String viaje_id;
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
