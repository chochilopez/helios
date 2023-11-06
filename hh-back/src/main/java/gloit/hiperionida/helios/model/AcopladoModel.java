package gloit.hiperionida.helios.model;


import lombok.*;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "acoplado")
public class AcopladoModel extends AbsVehiculoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
