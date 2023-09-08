package gloit.hiperionida.helios.model;


import lombok.*;

import jakarta.persistence.*;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "cliente")
public class ClienteModel extends AbsPersonaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}

