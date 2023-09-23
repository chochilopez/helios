package gloit.hiperionida.helios.util.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EmailDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String subject;
    private String cc;
    private String sender;
    private String reciever;
    private String body;
    private String sended;
}
