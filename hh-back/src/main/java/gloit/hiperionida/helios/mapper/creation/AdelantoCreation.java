package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdelantoCreation extends AbsAuditoriaCreation {
    private String fecha;
    private String monto;
    private String notas;
    private String rendido;
    private String cajaId;
    private String conductorId;
    private String reciboId;
}
