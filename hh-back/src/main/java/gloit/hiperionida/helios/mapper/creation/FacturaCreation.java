package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FacturaCreation extends AbsAuditoriaCreation {
    private String id;
    private String descuento;
    private String fecha;
    private String iva;
    private String numeroComprobante;
    private String recarga;
    private String subTotal;
    private String tipoComprobante;
    private String pagada;

    private String remitoId;
    private String viajeId;
}
