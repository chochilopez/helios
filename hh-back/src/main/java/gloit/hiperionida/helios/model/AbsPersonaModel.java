package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@Setter
@SuperBuilder
public abstract class AbsPersonaModel extends AbsAuditoriaModel {
    private String direccion;
    private String email;
    private String identificacion;
    private String nombre;
    @Column(columnDefinition = "TEXT")
    private String notas;
    private String telefono;

    public AbsPersonaModel() {
    }

    public AbsPersonaModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String direccion, String email, String identificacion, String nombre, String notas, String telefono) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.direccion = direccion;
        this.email = email;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.notas = notas;
        this.telefono = telefono;
    }
}
