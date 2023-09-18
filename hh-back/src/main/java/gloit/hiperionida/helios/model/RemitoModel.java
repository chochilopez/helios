package gloit.hiperionida.helios.model;

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
@Table(name = "remito")
public class RemitoModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private LocalDateTime fecha;
    private String numero;
}
