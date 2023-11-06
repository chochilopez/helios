package gloit.hiperionida.helios.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.Set;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "conductor")
public class ConductorModel extends AbsPersonaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long licenciaId;
}

