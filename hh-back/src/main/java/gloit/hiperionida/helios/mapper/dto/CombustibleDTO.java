package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Setter;
import java.io.Serializable;

@Setter
public class CombustibleDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String fecha;
    private String kilometros;
    private String litros;
    private String precio;
    private String notas;
    private CamionDTO camion;
    private ConductorDTO conductor;
    private ProveedorDTO proveedor;
}
