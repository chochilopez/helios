package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CombustibleCreation extends AbsAuditoriaCreation {
    private String id;
    private String fecha;
    private String kilometros;
    private String litros;
    private String precio;
    private String notas;
    private String camion_id;
    private String conductor_id;
    private String proveedor_id;
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
