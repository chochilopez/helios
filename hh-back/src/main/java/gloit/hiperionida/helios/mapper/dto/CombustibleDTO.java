package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
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
    /*
        private Long id;
    private LocalDateTime fecha;
    private Double kilometros;
    private Double litros;
    private Double precio;
    private String notas;

    private Long camion_id;
    private Long conductor_id;
    private Long proveedor_id;
     */
}
