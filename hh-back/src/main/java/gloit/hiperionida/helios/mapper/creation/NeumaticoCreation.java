package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.model.enums.EstadoNeumaticoEnum;
import gloit.hiperionida.helios.model.enums.UbicacionNeumaticoEnum;
import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
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
}
