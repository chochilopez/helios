package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CombustibleCreation extends AbsAuditoriaCreation {
    private String cajaId;
    private String camionId;
    private String conductorId;
    private String fecha;
    private String kilometros;
    private String litros;
    private String notas;
    private String precio;
    private String proveedorId;
}
