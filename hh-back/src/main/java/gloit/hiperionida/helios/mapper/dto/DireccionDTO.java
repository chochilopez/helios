package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class DireccionDTO extends AbsAuditoriaDTO implements Serializable {
    private String ciudad;
    private String completa;
    private String direccion;
    private String nombre;
    private String notas;

    public DireccionDTO() {
    }

    public DireccionDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String ciudad, String completa, String direccion, String nombre, String notas) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.ciudad = ciudad;
        this.completa = completa;
        this.direccion = direccion;
        this.nombre = nombre;
        this.notas = notas;
    }
}
