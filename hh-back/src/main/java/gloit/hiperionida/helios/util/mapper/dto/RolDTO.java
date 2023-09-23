package gloit.hiperionida.helios.util.mapper.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RolDTO implements Serializable {
    private String id;
    private String rol;
}
