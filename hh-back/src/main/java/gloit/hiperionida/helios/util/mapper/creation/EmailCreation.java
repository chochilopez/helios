package gloit.hiperionida.helios.util.mapper.creation;

import lombok.Getter;

@Getter
public class EmailCreation extends AbsAuditoriaCreation {
    private String id;
    private String subject;
    private String sender;
    private String reciever;
    private String body;
}
