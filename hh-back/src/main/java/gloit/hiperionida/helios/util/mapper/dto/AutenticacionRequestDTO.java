package gloit.hiperionida.helios.util.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AutenticacionRequestDTO {
    private String username;
    private String password;
}
