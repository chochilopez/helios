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
    /*
        private Long id;
    private LocalDateTime fechaCompra;
    private Double kmVida;
    private Double kmActuales;
    private Double kmRecapado;
    private String numeroRemito;
    private String marca ;
    private Double precioCompra;
    private Integer recapadosMaximos;
    @Enumerated(EnumType.STRING)
    private UbicacionNeumaticoEnum ubicacion;
    @Enumerated(EnumType.STRING)
    private EstadoNeumaticoEnum estado;
    private Boolean baja;

    private Long acoplado_id;
    private Long camion_id;
    private Long proveedor_id;
     */
}
