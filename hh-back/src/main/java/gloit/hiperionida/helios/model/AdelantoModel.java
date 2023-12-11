package gloit.hiperionida.helios.model;

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
@Table(name = "adelanto")
public class AdelantoModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    private Double monto;
    @Column(columnDefinition = "TEXT")
    private String notas;
    private Boolean rendido;

    private Long cajaId;
    private Long conductorId;
    private Long reciboId;
}
