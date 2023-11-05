package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


@Getter
@MappedSuperclass
@Setter
public abstract class AbsVehiculoCreation extends AbsAuditoriaCreation {
    private	String cantidadNeumaticos;
    private	String marca;
    private String modelo;
    private String anio;
    private String patente;
    private String peso;
    /*
        private	String cantidadNeumaticos;
    private	String marca;
    private String modelo;
    private String anio;
    private String patente;
    private String peso;
     */
}
