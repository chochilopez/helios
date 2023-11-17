package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.MovimientoEnum;
import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
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
@Table(name = "cuenta_corriente")
public class CuentaCorrienteModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double monto;
    @Column(columnDefinition = "TEXT")
    private String notas;
    @Enumerated(EnumType.STRING)
    private TipoPagoEnum tipoPago;
    @Enumerated(EnumType.STRING)
    private MovimientoEnum tipoMovimiento;
    private LocalDateTime fecha;

    private Long clienteId;
    private Long reciboId;
    private Long facturaId;
}
