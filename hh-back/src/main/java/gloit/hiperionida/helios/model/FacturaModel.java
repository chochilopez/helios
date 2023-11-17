package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.CondicionPagoEnum;
import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
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
@Table(name = "factura")
public class FacturaModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double bonificacion;
    private Integer cantidad;
    private String codigo;
    @Column(columnDefinition = "TEXT")
    private String concepto;
    @Enumerated(EnumType.STRING)
    private CondicionPagoEnum condicionPagoEnum;
    private String domicilioComercial;
    private LocalDateTime fechaEmision;
    private Long fechaVencimientoId;
    private Double iva;
    private String notas;
    private String numeroComprobante;
    private Double otrosImpuestos;
    private Boolean pagada;
    private Double precioUnitario;
    private String razonSocial;
    private Long remitoId;
    @Enumerated(EnumType.STRING)
    private TipoComprobanteEnum tipoComprobante;
    private Long viajeId;
}
