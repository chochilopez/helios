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
    private Double kmVida;
    private Double kmActuales;
    private Double kmRecapado;
    private String marca ;
    private Double precioCompra;
    private Integer recapadosMaximos;
    @Enumerated(EnumType.STRING)
    private UbicacionNeumaticoEnum ubicacion;
    @Enumerated(EnumType.STRING)
    private EstadoNeumaticoEnum estado;
    private Boolean baja;

    private Long acopladoId;
    private Long camionId;
    private Long proveedorId;
}

