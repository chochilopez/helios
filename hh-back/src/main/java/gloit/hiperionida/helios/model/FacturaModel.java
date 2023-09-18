package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

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
    private Double descuento;
    private LocalDateTime fecha;
    private Double iva;
    private String numeroComprobante;
    private Double recarga;
    private Double subTotal;
    @Enumerated(EnumType.STRING)
    private TipoComprobanteEnum tipoComprobante;
    private Boolean pagada;

    @OneToOne
    private MovimientoModel movimiento;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private Set<PagoModel> pagos;

    @OneToOne
    private RemitoModel remito;

    @OneToOne
    private ViajeModel viaje;
}
