package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;


@Getter
@MappedSuperclass
@Setter
public abstract class AbsVehiculoModel extends AbsAuditoriaModel {
    private	Integer cantidadNeumaticos;
    private	String marcaModelo;
    private Integer anio;
    private String patente;
    private String peso;
    @Column(columnDefinition = "TEXT")
    private String notas;


    private Long seguroId;
}
