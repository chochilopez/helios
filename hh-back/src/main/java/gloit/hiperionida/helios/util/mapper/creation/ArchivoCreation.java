package gloit.hiperionida.helios.util.mapper.creation;

import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ArchivoCreation extends AbsAuditoriaCreation {
    private String id;
    private String path;
    private String nombre;
    private String tipo;
    private String tamanio;
    private String descripcion;
}
