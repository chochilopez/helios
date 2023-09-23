package gloit.hiperionida.helios.mapper.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ClienteDTO extends AbsPersonaDTO implements Serializable {
    private String id;
}
