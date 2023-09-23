package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ChequeDTO extends AbsAuditoriaDTO implements Serializable {
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
}
