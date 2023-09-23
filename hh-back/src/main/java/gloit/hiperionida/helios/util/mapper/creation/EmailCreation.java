package gloit.hiperionida.helios.util.mapper.creation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EmailCreation extends AbsAuditoriaCreation {
    private String id;
    private String subject;
    private String sender;
    private String reciever;
    private String body;
}
