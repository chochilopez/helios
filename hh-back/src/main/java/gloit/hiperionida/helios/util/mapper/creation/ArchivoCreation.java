package gloit.hiperionida.helios.util.mapper.creation;

import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ArchivoCreation extends AbsAuditoriaCreation {
    private String id;
    private String descripcion;
    private String nombre;
    private String path;
    private String tamanio;
    private String tipo;
}
