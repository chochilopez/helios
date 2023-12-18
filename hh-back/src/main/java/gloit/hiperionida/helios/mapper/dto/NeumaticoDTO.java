package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NeumaticoDTO extends AbsAuditoriaDTO implements Serializable {
    private String fechaCompra;
    private String kmVida;
    private String kmActuales;
    private String kmRecapado;
    private String marca ;
    private String precioCompra;
    private String recapadosMaximos;
    private String ubicacion;
    private String estado;
    private String baja;
    private String acopladoId;
    private String camionId;
    private String proveedor;

    public NeumaticoDTO() {
    }

    public NeumaticoDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String fechaCompra, String kmVida, String kmActuales, String kmRecapado, String marca, String precioCompra, String recapadosMaximos, String ubicacion, String estado, String baja, String acopladoId, String camionId, String proveedor) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.fechaCompra = fechaCompra;
        this.kmVida = kmVida;
        this.kmActuales = kmActuales;
        this.kmRecapado = kmRecapado;
        this.marca = marca;
        this.precioCompra = precioCompra;
        this.recapadosMaximos = recapadosMaximos;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.baja = baja;
        this.acopladoId = acopladoId;
        this.camionId = camionId;
        this.proveedor = proveedor;
    }
}
