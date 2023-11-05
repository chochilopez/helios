package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ChequeCreation extends AbsAuditoriaCreation {
    private String id;
    private String cuitEmisor;
    private String destinatario;
    private String emisor;
    private String estado;
    private String fechaCobro;
    private String fechaEmision;
    private String monto;
    private String numeroCheque;
    private String banco;
    private String entregadoA;
    private String recibidoDe;
        /*
        private Long id;
    private String cuitEmisor;
    private String destinatario;
    private String emisor;
    @Enumerated(EnumType.STRING)
    private EstadoChequeEnum estado;
    private LocalDateTime fechaCobro;
    private LocalDateTime fechaEmision;
    private Double monto;
    private String numeroCheque;
    private String banco;
    private String entregadoA;
    private String recibidoDe;
     */
}
