package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ConciliacionBancoCreation extends AbsAuditoriaCreation {
    private String id;
    private String movimiento;
    private String fecha;
    private String concepto;
    private String monto;
    private String banco_id;
        /*
        private Long id;
    @Enumerated(EnumType.STRING)
    private MovimientoEnum movimiento;
    private LocalDateTime fecha;
    @Column(columnDefinition = "TEXT")
    private String concepto;
    private Double monto;

    private Long banco_id;
     */
}
