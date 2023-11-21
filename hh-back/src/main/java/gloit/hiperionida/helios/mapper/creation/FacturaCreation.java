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
    private String bonificacionPercentil;
    private String bonificacionMonto;
    private String cantidad;
    private String codigo;
    private String concepto;
    private String condicionPago;
    private String domicilioComercial;
    private String fechaEmision;
    private String fechaVencimiento;
    private String fechaVencimientoId;
    private String ivaPercentil;
    private String ivaMonto;
    private String notas;
    private String numeroComprobante;
    private String otrosImpuestosPercentil;
    private String otrosImpuestosMonto;
    private String pagada;
    private String precioUnitario;
    private String razonSocial;
    private String remitoId;
    private String subTotal;
    private String tipoComprobante;
    private String total;
    private String viajeId;
}