package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


@Getter
@MappedSuperclass
@Setter
public abstract class AbsVehiculoCreation extends AbsAuditoriaCreation {
    private String anio;
    private	String cantidadNeumaticos;
    private	String marcaModelo;
    private String notas;
    private String patente;
    private String peso;
}
