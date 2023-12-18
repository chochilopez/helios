package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
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

    public ChequeCreation() {
    }

    public ChequeCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String cuitEmisor, String destinatario, String emisor, String estado, String fechaCobro, String fechaEmision, String monto, String numeroCheque, String banco, String entregadoA, String recibidoDe) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
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
