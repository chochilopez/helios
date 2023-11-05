package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CategoriaGastoCreation extends AbsAuditoriaCreation {
    private String id;
    private String categoria;
        /*
        private Long id;
    private String categoria;
     */
}
