package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import gloit.hiperionida.helios.util.model.ArchivoModel;

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
@Table(name = "compra")
public class CompraModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    private Double iva;
    private String numeroComprobante;
    private Double subTotal;
    private TipoComprobanteEnum tipoComprobante;
    @Column(columnDefinition = "TEXT")
    private String notas;

    @OneToOne
    private ArchivoModel comprobante;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "compra_id")
    private Set<PagoModel> pagos;

    @ManyToOne
    private ProveedorModel proveedor;

    @OneToOne
    private RemitoModel remito;
}
