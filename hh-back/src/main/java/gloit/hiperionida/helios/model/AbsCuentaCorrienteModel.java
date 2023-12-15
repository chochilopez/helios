package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.MovimientoEnum;
import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsCuentaCorrienteModel extends AbsAuditoriaModel {
    private Double monto;
    @Column(columnDefinition = "TEXT")
    private String notas;
    @Enumerated(EnumType.STRING)
    private TipoPagoEnum tipoPago;
    @Enumerated(EnumType.STRING)
    private MovimientoEnum tipoMovimiento;
    private LocalDateTime fecha;
}
