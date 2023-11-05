package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdelantoCreation extends AbsAuditoriaCreation {
    private String id;
    private String descripcion;
    private String fecha;
    private String monto;
    private String notas;
    private String recibo;
    private String rendido;
    private String caja_id;
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
