package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import gloit.hiperionida.helios.util.model.ArchivoModel;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "pago")
public class PagoModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double monto;
    @Column(columnDefinition = "TEXT")
    private String notas;
    private TipoPagoEnum tipoPago;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "pago_id")
    private Set<ArchivoModel> comprobante;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "pago_id")
    private Set<ChequeModel> valores;

    @OneToOne
    private ReciboModel recibo;


}

