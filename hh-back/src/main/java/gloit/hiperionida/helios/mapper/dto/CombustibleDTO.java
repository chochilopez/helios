package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CombustibleDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
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
