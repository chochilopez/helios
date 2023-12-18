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
@Table(name = "combustible")
public class CombustibleModel extends AbsAuditoriaModel {
    private LocalDateTime fecha;
    private Double kilometros;
    private Double litros;
    private Double precio;
    @Column(columnDefinition = "TEXT")
    private String notas;

    private Long cajaId;
    private Long camionId;
    private Long conductorId;
    private Long proveedorId;

    public CombustibleModel() {
    }

    public CombustibleModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, LocalDateTime fecha, Double kilometros, Double litros, Double precio, String notas, Long cajaId, Long camionId, Long conductorId, Long proveedorId) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.fecha = fecha;
        this.kilometros = kilometros;
        this.litros = litros;
        this.precio = precio;
        this.notas = notas;
        this.cajaId = cajaId;
        this.camionId = camionId;
        this.conductorId = conductorId;
        this.proveedorId = proveedorId;
    }
}

