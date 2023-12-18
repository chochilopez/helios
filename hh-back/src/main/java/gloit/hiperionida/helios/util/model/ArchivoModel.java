package gloit.hiperionida.helios.util.model;

import gloit.hiperionida.helios.util.model.enums.TipoArchivoEnum;
import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "archivo")
public class ArchivoModel extends AbsAuditoriaModel {
    private String descripcion;
    private String nombre;
    private String path;
    private String tamanio;
    @Enumerated(EnumType.STRING)
    private TipoArchivoEnum tipo;

    public ArchivoModel() {
    }

    public ArchivoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String descripcion, String nombre, String path, String tamanio, TipoArchivoEnum tipo) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.path = path;
        this.tamanio = tamanio;
        this.tipo = tipo;
    }
}