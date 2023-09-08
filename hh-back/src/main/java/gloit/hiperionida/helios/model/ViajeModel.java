package gloit.hiperionida.helios.model;


import lombok.*;

import jakarta.persistence.*;
import java.util.Set;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "viaje")
public class ViajeModel extends AbsViajeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean cancelada;
    private String guia;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "viaje_id")
    private Set<GastoModel> gastos;

    @ManyToOne()
    private ClienteModel intermediario;


    @ManyToOne()
    private ConductorModel conductor;

    @ManyToOne()
    private CamionModel camion;

    @ManyToOne()
    private AcopladoModel acoplado;
}

