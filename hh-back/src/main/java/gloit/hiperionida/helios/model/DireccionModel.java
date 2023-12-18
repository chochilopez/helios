package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;

import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "direccion")
public class DireccionModel extends AbsAuditoriaModel {
    private String ciudad;
    private String direccion;
    private String nombre;
    @Column(columnDefinition = "TEXT")
    private String notas;

    public DireccionModel() {
    }

    public DireccionModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String ciudad, String direccion, String nombre, String notas) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.nombre = nombre;
        this.notas = notas;
    }
}
