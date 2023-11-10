package gloit.hiperionida.helios.mapper.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ViajeDTO extends AbsViajeDTO implements Serializable {
    private String id;
    private String acoplado;
    private String camion;
    private String carga;
    private String conductor;
    private String guia;
    private String intermediario;
    private String kmVacio;
    private String neto;
    private String vendedor;
}

