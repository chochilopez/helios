package gloit.hiperionida.helios.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "cuenta_corriente_cliente")
public class CuentaCorrienteClienteModel extends AbsCuentaCorrienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;
    private Long reciboId;
    private Long facturaId;
}
