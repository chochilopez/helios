package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;


@Getter
@MappedSuperclass
@Setter
public abstract class AbsVehiculoModel extends AbsAuditoriaModel {
    private	int cantidadNeumaticos;
    private	String identificacion;
    private String modelo;
    private String patente;
    private double peso;
}
