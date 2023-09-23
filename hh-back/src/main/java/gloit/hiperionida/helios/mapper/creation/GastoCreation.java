package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GastoCreation extends AbsAuditoriaCreation {
    private String id;
    private String monto;
    private String notas;
    private String categoriaGasto_id;
}
