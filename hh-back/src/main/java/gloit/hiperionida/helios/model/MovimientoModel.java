package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "movimiento")
public class MovimientoModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

// TODO movimiento de cuenta puede ser pagado o no
}

