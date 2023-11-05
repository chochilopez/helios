package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdelantoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String descripcion;
    private String fecha;
    private String monto;
    private String notas;
    private String recibo;
    private String rendido;
    private CajaDTO caja;
        /*
        private Long id;
    private String descripcion;
    private LocalDateTime fecha;
    private Double monto;
    @Column(columnDefinition = "TEXT")
    private String notas;
    private String recibo;
    private Boolean rendido;

    private Long caja_id;
    private Long conductor_id;
     */
}
