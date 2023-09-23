package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NeumaticoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String fechaCompra;
    private String kmVida;
    private String kmActuales;
    private String kmRecapado;
    private String numeroRemito;
    private String marca ;
    private String precioCompra;
    private String recapadosMaximos;
    private String ubicacion;
    private String estado;
    private String baja;
    private ProveedorDTO proveedor;
}
