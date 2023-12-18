package gloit.hiperionida.helios.util.mapper.creation;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class EmailCreation extends AbsAuditoriaCreation {
    private String subject;
    private String sender;
    private String reciever;
    private String body;

    public EmailCreation() {
    }

    public EmailCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String subject, String sender, String reciever, String body) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.subject = subject;
        this.sender = sender;
        this.reciever = reciever;
        this.body = body;
    }
}
