package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;

@Getter
public class GastoCreation extends AbsAuditoriaCreation {
    private String id;
    private String monto;
    private String notas;
    private String categoriaGasto_id;
    private String viaje_id;
}
