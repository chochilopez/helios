package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FacturaDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String bonificacion;
    private String cantidad;
    private String codigo;
    private String concepto;
    private String condicionPagoEnum;
    private String domicilioComercial;
    private String fechaEmision;
    private String fechaVencimiento;
    private String fechaVencimientoId;
    private String iva;
    private String notas;
    private String numeroComprobante;
    private String otrosImpuestos;
    private String pagada;
    private String precioUnitario;
    private String razonSocial;
    private String tipoComprobante;

    private String camion;
    private String cantidadTransportada;
    private String categoriaViaje;
    private String comprador;
    private String conductor;
    private String destino;
    private String fechaViaje;
    private String kmCargado;
    private String numeroGuia;
    private String numeroRemito;
    private String origen;
    private String valorKm;
}