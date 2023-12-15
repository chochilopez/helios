package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DireccionCreation extends AbsAuditoriaCreation {
    private String ciudad;
    private String direccion;
    private String nombre;
    private String notas;
}
