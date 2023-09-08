package gloit.hiperionida.helios.util.mapper.dto;

import lombok.Setter;

import java.io.Serializable;

@Setter
public class EmailDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String subject;
    private String cc;
    private String sender;
    private String reciever;
    private String body;
    private String sended;
}
