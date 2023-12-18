package gloit.hiperionida.helios.util.mapper.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class EmailDTO extends AbsAuditoriaDTO implements Serializable {
    private String subject;
    private String cc;
    private String sender;
    private String reciever;
    private String body;
    private String sended;

    public EmailDTO() {
    }

    public EmailDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String subject, String cc, String sender, String reciever, String body, String sended) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.subject = subject;
        this.cc = cc;
        this.sender = sender;
        this.reciever = reciever;
        this.body = body;
        this.sended = sended;
    }
}
