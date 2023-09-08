package gloit.hiperionida.helios.model;


import lombok.*;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "camion")
public class CamionModel extends AbsVehiculoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroChasis;
    private String numeroMotor;
    private String pesoArrastre;

    @OneToOne()
    private SeguroModel seguro;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "camion_id")
    private Set<ServicioModel> servicios;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "camion_id")
    private Set<NeumaticoModel> neumaticos;
}
