package gloit.hiperionida.helios.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "presupuesto")
public class PresupuestoModel extends AbsViajeModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer validez;
    @Column(columnDefinition = "TEXT")
    private String notas;
}
