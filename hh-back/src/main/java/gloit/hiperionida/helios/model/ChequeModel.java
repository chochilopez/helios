package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.EstadoChequeEnum;
import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "cheque")
public class ChequeModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cuitEmisor;
    private String destinatario;
    private String emisor;
    private EstadoChequeEnum estado;
    private LocalDateTime fechaCobro;
    private LocalDateTime fechaEmision;
    private Double monto;
    private String numeroCheque;

    @ManyToOne()
    private BancoModel banco;

    @ManyToOne()
    private ProveedorModel entregadoA;

    @ManyToOne()
    private ClienteModel recibidoDe;

}
