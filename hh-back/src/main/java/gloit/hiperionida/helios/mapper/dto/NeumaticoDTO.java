package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.model.enums.EstadoNeumaticoEnum;
import gloit.hiperionida.helios.model.enums.UbicacionNeumaticoEnum;
import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
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
}
