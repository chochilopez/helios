package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BancoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String banco;
    private String notas;
        /*
        private Long id;
    private String banco;
    @Column(columnDefinition = "TEXT")
    private String notas;
     */
}
