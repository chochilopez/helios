package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CombustibleDTO extends AbsAuditoriaDTO implements Serializable {
    private String caja;
    private String cajaId;
    private String camion;
    private String camionId;
    private String conductor;
    private String conductorId;
    private String fecha;
    private String kilometros;
    private String litros;
    private String notas;
    private String precio;
    private String proveedor;
    private String proveedorId;
    private String total;

    public CombustibleDTO() {
    }

    public CombustibleDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String caja, String cajaId, String camion, String camionId, String conductor, String conductorId, String fecha, String kilometros, String litros, String notas, String precio, String proveedor, String proveedorId, String total) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.caja = caja;
        this.cajaId = cajaId;
        this.camion = camion;
        this.camionId = camionId;
        this.conductor = conductor;
        this.conductorId = conductorId;
        this.fecha = fecha;
        this.kilometros = kilometros;
        this.litros = litros;
        this.notas = notas;
        this.precio = precio;
        this.proveedor = proveedor;
        this.proveedorId = proveedorId;
        this.total = total;
    }
}
