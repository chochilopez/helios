package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;

@Getter
public class NeumaticoCreation extends AbsAuditoriaCreation {
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
    private String proveedor_id;
    private String camion_id;
    private String acoplado_id;
}
