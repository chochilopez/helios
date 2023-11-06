package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "compra")
public class CompraModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
