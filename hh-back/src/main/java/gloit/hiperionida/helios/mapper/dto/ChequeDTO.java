package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
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

    public ChequeDTO() {
    }

    public ChequeDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String cuitEmisor, String destinatario, String emisor, String estado, String fechaCobro, String fechaEmision, String monto, String numeroCheque, String banco, String entregadoA, String recibidoDe) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.cuitEmisor = cuitEmisor;
        this.destinatario = destinatario;
        this.emisor = emisor;
        this.estado = estado;
        this.fechaCobro = fechaCobro;
        this.fechaEmision = fechaEmision;
        this.monto = monto;
        this.numeroCheque = numeroCheque;
        this.banco = banco;
        this.entregadoA = entregadoA;
        this.recibidoDe = recibidoDe;
    }
}
