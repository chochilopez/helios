package gloit.hiperionida.helios.mapper.creation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ViajeCreation extends AbsViajeCreation {
    private String id;
    private String guia;
    private String conductor_id;
    private String camion_id;
    private String acoplado_id;
    private List<String> gastos_id;
}
