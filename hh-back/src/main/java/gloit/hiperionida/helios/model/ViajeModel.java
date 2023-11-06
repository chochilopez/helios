package gloit.hiperionida.helios.model;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "viaje")
public class ViajeModel extends AbsViajeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String guia;
    private Double neto;
    private Double kmCargado;
    private Double kmVacio;

    private Long acoplado_id;
    private Long camion_id;
    private Long carga_id;
    private Long conductor_id;
    private Long intermediario_id;
    private Long vendedor_id;
}

