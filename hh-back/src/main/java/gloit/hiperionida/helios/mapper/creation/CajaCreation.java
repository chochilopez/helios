package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CajaCreation extends AbsAuditoriaCreation {
    private String id;
    private String nombre;
    /*
        private Long id;
    private String nombre;
    private String notas;
     */
}
