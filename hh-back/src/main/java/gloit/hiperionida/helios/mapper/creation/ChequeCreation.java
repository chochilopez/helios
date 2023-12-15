package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.model.enums.EstadoChequeEnum;
import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ChequeCreation extends AbsAuditoriaCreation {
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
