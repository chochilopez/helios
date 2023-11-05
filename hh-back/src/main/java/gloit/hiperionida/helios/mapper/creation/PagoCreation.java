package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PagoCreation extends AbsAuditoriaCreation {
    private String id;
    private String monto;
    private String notas;
    private String tipoPago;
    private String recibo_id;
        /*
        private Long id;
    private Double monto;
    @Column(columnDefinition = "TEXT")
    private String notas;
    @Enumerated(EnumType.STRING)
    private TipoPagoEnum tipoPago;

    private Long compra_id;
    private Long factura_id;
    private Long recibo_id;
     */
}
