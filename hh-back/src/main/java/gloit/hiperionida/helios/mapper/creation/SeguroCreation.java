package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SeguroCreation extends AbsAuditoriaCreation {
    private String id;
    private String notas;

    private String acopladoId;
    private String aseguradoraId;
    private String camionId;
    private String vencimiento;
}
