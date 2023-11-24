package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CuentaCorrienteCreation extends AbsAuditoriaCreation {
    private String id;
    private String monto;
    private String notas;
    private String tipoPago;
    private String tipoMovimiento;

    private String clienteId;
    private String reciboId;
    private String facturaId;
}