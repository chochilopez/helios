package gloit.hiperionida.helios.mapper.creation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CuentaCorrienteClienteCreation extends AbsCuentaCorrienteCreation {
    private String clienteId;
    private String reciboId;
    private String facturaId;
}