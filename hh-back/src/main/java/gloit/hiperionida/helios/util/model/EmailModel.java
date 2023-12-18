package gloit.hiperionida.helios.util.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "email")
public class EmailModel extends AbsAuditoriaModel {
    private String subject;
    private String cc;
    private String sender;
    private String reciever;
    @Column(columnDefinition = "TEXT")
    private String body;
    private LocalDateTime sended;

    public EmailModel() {
    }

    public EmailModel(String subject, String carbonCopy, String sender, String reciever, String body) {
        this.subject = subject;
        this.cc = carbonCopy;
        this.sender = sender;
        this.reciever = reciever;
        this.body = body;
    }

    public EmailModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String subject, String cc, String sender, String reciever, String body, LocalDateTime sended) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.subject = subject;
        this.cc = cc;
        this.sender = sender;
        this.reciever = reciever;
        this.body = body;
        this.sended = sended;
    }
}