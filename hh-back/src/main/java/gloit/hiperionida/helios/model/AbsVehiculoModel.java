package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Getter
@MappedSuperclass
@Setter
@SuperBuilder
public abstract class AbsVehiculoModel extends AbsAuditoriaModel {
    private Integer anio;
    private	Integer cantidadNeumaticos;
    private	String marcaModelo;
    @Column(columnDefinition = "TEXT")
    private String notas;
    private String patente;
    private String peso;

    public AbsVehiculoModel() {
    }

    public AbsVehiculoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, Integer anio, Integer cantidadNeumaticos, String marcaModelo, String notas, String patente, String peso) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.anio = anio;
        this.cantidadNeumaticos = cantidadNeumaticos;
        this.marcaModelo = marcaModelo;
        this.notas = notas;
        this.patente = patente;
        this.peso = peso;
    }
}
