package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;
import java.util.List;

@Getter
public class CompraCreation extends AbsAuditoriaCreation {
    private String id;
    private String fecha;
    private String iva;
    private String numeroComprobante;
    private String subTotal;
    private String tipoComprobante;
    private String notas;
    private String comprobante_id;
    private List<String> pagos_id;
    private String proveedor_id;
    private String remito_id;
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
