package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.EstadoChequeEnum;
import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "cheque")
public class ChequeModel extends AbsAuditoriaModel {
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

    public ChequeModel() {
    }

    public ChequeModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String cuitEmisor, String destinatario, String emisor, EstadoChequeEnum estado, LocalDateTime fechaCobro, LocalDateTime fechaEmision, Double monto, String numeroCheque, String banco, String entregadoA, String recibidoDe) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
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
