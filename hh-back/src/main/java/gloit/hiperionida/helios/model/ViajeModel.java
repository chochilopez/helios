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
    private Double kmVacio;
    private Double neto;

    private Long acopladoId;
    private Long camionId;
    private Long cargaId;
    private Long conductorId;
    private Long intermediarioId;
    private Long vendedorId;
}

