package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;


@Getter
@MappedSuperclass
@Setter
public abstract class AbsVehiculoModel extends AbsAuditoriaModel {
    private Integer anio;
    private	Integer cantidadNeumaticos;
    private	String marcaModelo;
    @Column(columnDefinition = "TEXT")
    private String notas;
    private String patente;
    private String peso;
}
