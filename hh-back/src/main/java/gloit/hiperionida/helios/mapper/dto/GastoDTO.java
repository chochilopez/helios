package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GastoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String monto;
    private String notas;
    private CategoriaGastoDTO categoriaGasto;
    /*
        private Long id;
    private Double monto;
    @Column(columnDefinition = "TEXT")
    private String notas;

    private Long categoriaGasto_id;
    private Long viaje_id;
}
     */
}
