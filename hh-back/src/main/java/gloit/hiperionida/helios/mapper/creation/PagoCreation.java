package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;

import java.util.List;

@Getter
public class PagoCreation extends AbsAuditoriaCreation {
    private String id;
    private String monto;
    private String notas;
    private String tipoPago;
    private List<String> comprobante_id;
    private List<String> valores_id;
    private String recibo_id;
}
