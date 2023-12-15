package gloit.hiperionida.helios.mapper.creation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CuentaCorrienteProveedorCreation extends AbsCuentaCorrienteCreation {
    private String proveedorId;
}