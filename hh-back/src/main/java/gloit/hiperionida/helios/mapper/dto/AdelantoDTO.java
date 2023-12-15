package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdelantoDTO extends AbsAuditoriaDTO implements Serializable {
    private String fecha;
    private String monto;
    private String notas;
    private String rendido;
    private String caja;
    private String cajaId;
    private String conductor;
    private String conductorId;
    private String recibo;
    private String reciboId;
}
