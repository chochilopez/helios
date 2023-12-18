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
public abstract class AbsPersonaDTO extends AbsAuditoriaDTO {
    private String email;
    private String identificacion;
    private String nombre;
    private String notas;
    private String telefono;
    private String direccion;

    public AbsPersonaDTO() {
    }

    public AbsPersonaDTO(String id, String creada, String creador, String creadorId, String modificada, String modificador, String modificadorId, String eliminada, String eliminador, String eliminadorId, String email, String identificacion, String nombre, String notas, String telefono, String direccion) {
        super(id, creada, creador, creadorId, modificada, modificador, modificadorId, eliminada, eliminador, eliminadorId);
        this.email = email;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.notas = notas;
        this.telefono = telefono;
        this.direccion = direccion;
    }
}
