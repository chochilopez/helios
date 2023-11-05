package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NeumaticoCreation extends AbsAuditoriaCreation {
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
    private String proveedor_id;
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
