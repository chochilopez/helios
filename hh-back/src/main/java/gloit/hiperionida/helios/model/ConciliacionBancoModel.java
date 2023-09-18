package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.MovimientoEnum;
import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "conciliacion_banco")
public class ConciliacionBancoModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MovimientoEnum movimiento;
    private LocalDateTime fecha;
    @Column(columnDefinition = "TEXT")
    private String concepto;
    private Double monto;

    @ManyToOne()
    private BancoModel banco;
}
