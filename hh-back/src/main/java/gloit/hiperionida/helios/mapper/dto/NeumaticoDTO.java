package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Setter;
import java.io.Serializable;

@Setter
public class NeumaticoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String fechaCompra;
    private String kilometros;
    private String kilometrosActuales;
    private String kilometrosRecapado;
    private String numeroRemito;
    private String marca ;
    private String precioCompra;
    private String recapadosMaximos;
    private String ubicacion;
    private String estado;
    private String baja;
    private ProveedorDTO proveedor;
    private CamionDTO camion;
    private AcopladoDTO acoplado;
}
