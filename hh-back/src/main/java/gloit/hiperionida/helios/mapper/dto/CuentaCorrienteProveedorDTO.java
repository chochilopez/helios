package gloit.hiperionida.helios.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CuentaCorrienteProveedorDTO extends AbsCuentaCorrienteDTO implements Serializable {
    private String proveedorId;
    private String proveedor;
}