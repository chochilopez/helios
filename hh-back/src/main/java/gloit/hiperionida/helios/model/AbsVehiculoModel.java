package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;


@Getter
@MappedSuperclass
@Setter
public abstract class AbsVehiculoModel extends AbsAuditoriaModel {
    private	String cantidadNeumaticos;
    private	String marca;
    private String modelo;
    private String anio;
    private String patente;
    private String peso;

    private Long seguro_id;
}
