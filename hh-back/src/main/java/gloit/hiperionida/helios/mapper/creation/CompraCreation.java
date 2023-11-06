package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CompraCreation extends AbsAuditoriaCreation {
    private String id;
    private String fecha;
    private String iva;
    private String numeroComprobante;
    private String subTotal;
    private String tipoComprobante;
    private String notas;
    private String comprobante_id;
    private String proveedor_id;
}
