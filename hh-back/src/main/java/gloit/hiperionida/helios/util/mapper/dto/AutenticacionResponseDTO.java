package gloit.hiperionida.helios.util.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AutenticacionResponseDTO {
    private String tokenAcceso;
    private List<String> roles;
}
