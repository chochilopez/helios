package gloit.hiperionida.helios.util.model;

import gloit.hiperionida.helios.util.model.enums.TipoArchivoEnum;
import lombok.*;
import jakarta.persistence.*;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "archivo")
public class ArchivoModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String nombre;
    private String path;
    private String tamanio;
    private TipoArchivoEnum tipo;
}