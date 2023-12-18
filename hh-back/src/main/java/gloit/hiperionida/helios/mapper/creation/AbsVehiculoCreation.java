package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@MappedSuperclass
@Setter
@SuperBuilder
public abstract class AbsVehiculoCreation extends AbsAuditoriaCreation {
    private String anio;
    private	String cantidadNeumaticos;
    private	String marcaModelo;
    private String notas;
    private String patente;
    private String peso;

    public AbsVehiculoCreation() {
    }

    public AbsVehiculoCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String anio, String cantidadNeumaticos, String marcaModelo, String notas, String patente, String peso) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
        this.anio = anio;
        this.cantidadNeumaticos = cantidadNeumaticos;
        this.marcaModelo = marcaModelo;
        this.notas = notas;
        this.patente = patente;
        this.peso = peso;
    }
}
