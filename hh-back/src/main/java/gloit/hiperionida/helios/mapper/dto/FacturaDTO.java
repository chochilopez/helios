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
    private String notas;
    private String numeroComprobante;
    private String recarga;
    private String subTotal;
    private String tipoComprobante;
    private String pagada;

    private String camion;
    private String cantidadTransportada;
    private String categoriaViaje;
    private String comprador;
    private String conductor;
    private String destino;
    private String fechaViaje;
    private String kmCargado;
    private String numeroGuia;
    private String origen;
    private String remito;
    private String valorKm;
}
/*
    private String id;
    private String camion;
    private String cantidadTransportada;
    private String categoriaViaje;
    private String comprador;
    private String conductor;
    private String descuento;
    private String destino;
    private String fecha;
    private String fechaViaje;
    private String iva;
    private String kmCargado;
    private String numeroComprobante;
    private String numeroGuia;
    private String origen;
    private String pagada;
    private String recarga;
    private String remito;
    private String subTotal;
    private String tipoComprobante;
    private String valorKm;

 */