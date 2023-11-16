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
    private String kmVacio;
    private String neto;

    private String acoplado;
    private String acopladoId;
    private String camion;
    private String camionId;
    private String carga;
    private String cargaId;
    private String conductor;
    private String conductorId;
    private String intermediario;
    private String intermediarioId;
    private String vendedor;
    private String vendedorId;

    private String tipoComprobante;
    private String numeroComprobante;
    private String pagada;
}

