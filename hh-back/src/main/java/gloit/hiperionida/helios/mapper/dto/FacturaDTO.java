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
    private String descuento;
    private String fecha;
    private String iva;
    private String numeroComprobante;
    private String recarga;
    private String subTotal;
    private String tipoComprobante;
    private String pagada;

    private String remito;
    private String comprador;
    private String fechaViaje;
    private String numeroGuia;
    private String origen;
    private String destino;
    private String conductor;
    private String camion;
    private String categoriaViaje;
    private String cantidadTransportada;
}