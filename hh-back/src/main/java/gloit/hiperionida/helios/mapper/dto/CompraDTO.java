package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import gloit.hiperionida.helios.util.mapper.dto.ArchivoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CompraDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String fecha;
    private String iva;
    private String numeroComprobante;
    private String subTotal;
    private String tipoComprobante;
    private String notas;
    private String comprobante_id;
    private String proveedor_id;
    private String remito_id;
}
