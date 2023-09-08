package gloit.hiperionida.helios.model;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "servicio")
public class ServicioModel extends AbsVehiculoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double kmServicio;
    private Double kmActual;
    private Double kmProximo;

    private String tipo;
}
