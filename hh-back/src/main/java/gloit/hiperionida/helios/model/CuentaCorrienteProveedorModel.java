package gloit.hiperionida.helios.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "cuenta_corriente_proveedor")
public class CuentaCorrienteProveedorModel extends AbsCuentaCorrienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long proveedorId;
}
