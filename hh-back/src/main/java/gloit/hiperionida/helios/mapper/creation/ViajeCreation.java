package gloit.hiperionida.helios.mapper.creation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ViajeCreation extends AbsViajeCreation {
    private String id;
    private String guia;
    private String neto;
    private String kmCargado;
    private String kmVacio;
    private String acoplado_id;
    private String camion_id;
    private String carga_id;
    private String conductor_id;
    private String intermediario_id;
    private String vendedor_id;
}
