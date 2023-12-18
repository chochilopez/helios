package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CombustibleCreation extends AbsAuditoriaCreation {
    private String cajaId;
    private String camionId;
    private String conductorId;
    private String fecha;
    private String kilometros;
    private String litros;
    private String notas;
    private String precio;
    private String proveedorId;

    public CombustibleCreation() {
    }

    public CombustibleCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String cajaId, String camionId, String conductorId, String fecha, String kilometros, String litros, String notas, String precio, String proveedorId) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.cajaId = cajaId;
        this.camionId = camionId;
        this.conductorId = conductorId;
        this.fecha = fecha;
        this.kilometros = kilometros;
        this.litros = litros;
        this.notas = notas;
        this.precio = precio;
        this.proveedorId = proveedorId;
    }
}
