package gloit.hiperionida.helios.util.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "email")
public class EmailModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String cc;
    private String sender;
    private String reciever;
    @Column(columnDefinition = "TEXT")
    private String body;
    private LocalDateTime sended;

    public EmailModel(String subject, String carbonCopy, String sender, String reciever, String body) {
        this.subject = subject;
        this.cc = carbonCopy;
        this.sender = sender;
        this.reciever = reciever;
        this.body = body;
    }
}