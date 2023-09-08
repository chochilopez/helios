package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.EstadoNeumaticoEnum;
import gloit.hiperionida.helios.model.enums.UbicacionNeumaticoEnum;
import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "neumatico")
public class NeumaticoModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fechaCompra;
    private Double kilometros;
    private Double kilometrosActuales;
    private Double kilometrosRecapado;
    private String numeroRemito;
    private String marca ;
    private Double precioCompra;
    private Integer recapadosMaximos;
    private UbicacionNeumaticoEnum ubicacion;
    private EstadoNeumaticoEnum estado;
    private Boolean baja;

    @ManyToOne
    private ProveedorModel proveedor;
}

