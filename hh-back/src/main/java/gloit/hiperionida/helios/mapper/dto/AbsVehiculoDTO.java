package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@Setter
@SuperBuilder
public abstract class AbsVehiculoDTO extends AbsAuditoriaDTO {
    private String anio;
    private	String cantidadNeumaticos;
    private	String marcaModelo;
    private String notas;
    private String patente;
    private String peso;

    public AbsVehiculoDTO() {
    }

    public AbsVehiculoDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String anio, String cantidadNeumaticos, String marcaModelo, String notas, String patente, String peso) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.anio = anio;
        this.cantidadNeumaticos = cantidadNeumaticos;
        this.marcaModelo = marcaModelo;
        this.notas = notas;
        this.patente = patente;
        this.peso = peso;
    }
}
