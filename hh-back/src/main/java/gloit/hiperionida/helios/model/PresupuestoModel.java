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
    private LocalDateTime validez;
    private Double cantidadKm;

    private Double cantidadTransportada;
    private Double valorKm;
    @Column(columnDefinition = "TEXT")
    private String notas;
    private Long categoriaViaje_id;
    private Long origen_id;
    private Long destino_id;
    private Long comprador_id;
    private Long fecha_id;
}
