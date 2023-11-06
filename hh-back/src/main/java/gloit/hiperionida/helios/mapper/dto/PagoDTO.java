package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PagoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String monto;
    private String notas;
    private String tipoPago;
    private String compraId;
    private String facturaId;
    private String recibo;
}
