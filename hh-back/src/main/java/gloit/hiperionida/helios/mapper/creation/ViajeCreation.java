package gloit.hiperionida.helios.mapper.creation;

import lombok.Getter;
import java.util.List;

@Getter
public class ViajeCreation extends AbsViajeCreation {
    private String id;
    private String cancelada;
    private String guia;
    private List<String> gastos_id;
    private String intermediario_id;
    private String conductor_id;
    private String camion_id;
    private String acoplado_id;
}
