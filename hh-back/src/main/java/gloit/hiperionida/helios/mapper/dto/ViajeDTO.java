package gloit.hiperionida.helios.mapper.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ViajeDTO extends AbsViajeDTO implements Serializable {
    private String id;
    private String guia;
    private String neto;
    private String kmCargado;
    private String kmVacio;

    private String conductor;
    private String camion;
    private String acoplado;
    private String carga;
    private String vendedor;
    private String intermediario;
}
