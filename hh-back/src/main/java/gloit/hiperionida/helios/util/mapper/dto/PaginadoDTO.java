package gloit.hiperionida.helios.util.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaginadoDTO {
    String direccion;
    String campo;
    int pagina;
    int elementos;
}
