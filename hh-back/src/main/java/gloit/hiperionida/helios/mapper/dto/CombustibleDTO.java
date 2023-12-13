package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CombustibleDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String caja;
    private String cajaId;
    private String camion;
    private String camionId;
    private String conductor;
    private String conductorId;
    private String fecha;
    private String kilometros;
    private String litros;
    private String notas;
    private String precio;
    private String proveedor;
    private String proveedorId;
}
