package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NeumaticoCreation extends AbsAuditoriaCreation {
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
    private String proveedorId;

    public NeumaticoCreation() {
    }

    public NeumaticoCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String fechaCompra, String kmVida, String kmActuales, String kmRecapado, String marca, String precioCompra, String recapadosMaximos, String ubicacion, String estado, String baja, String acopladoId, String camionId, String proveedorId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
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
        this.proveedorId = proveedorId;
    }
}
