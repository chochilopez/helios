package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.model.enums.EstadoChequeEnum;
import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ChequeDTO extends AbsAuditoriaDTO implements Serializable {
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
