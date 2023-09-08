package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;

import lombok.*;
import jakarta.persistence.*;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "gasto")
public class GastoModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double monto;
    @Column(columnDefinition = "TEXT")
    private String notas;

    @ManyToOne()
    private CategoriaGastoModel categoriaGasto;
    // TODO Caja?

    @ManyToOne()
    @JoinColumn(name = "gastos_id")
    private ViajeModel viaje;
}

