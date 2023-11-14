package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsVehiculoDTO extends AbsAuditoriaDTO {
    private String anio;
    private	String cantidadNeumaticos;
    private	String marcaModelo;
    private String notas;
    private String patente;
    private String peso;
}
