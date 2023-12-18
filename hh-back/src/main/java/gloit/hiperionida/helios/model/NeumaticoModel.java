package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.EstadoNeumaticoEnum;
import gloit.hiperionida.helios.model.enums.UbicacionNeumaticoEnum;
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
@Table(name = "neumatico")
public class NeumaticoModel extends AbsAuditoriaModel {
    private LocalDateTime fechaCompra;
    private Double kmVida;
    private Double kmActuales;
    private Double kmRecapado;
    private String marca ;
    private Double precioCompra;
    private Integer recapadosMaximos;
    @Enumerated(EnumType.STRING)
    private UbicacionNeumaticoEnum ubicacion;
    @Enumerated(EnumType.STRING)
    private EstadoNeumaticoEnum estado;
    private Boolean baja;
    @Column(columnDefinition = "TEXT")
    private String notas;

    private Long acopladoId;
    private Long camionId;
    private Long proveedorId;

    public NeumaticoModel() {
    }

    public NeumaticoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, LocalDateTime fechaCompra, Double kmVida, Double kmActuales, Double kmRecapado, String marca, Double precioCompra, Integer recapadosMaximos, UbicacionNeumaticoEnum ubicacion, EstadoNeumaticoEnum estado, Boolean baja, String notas, Long acopladoId, Long camionId, Long proveedorId) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.fechaCompra = fechaCompra;
        this.kmVida = kmVida;
        this.kmActuales = kmActuales;
        this.kmRecapado = kmRecapado;
        this.marca = marca;
        this.precioCompra = precioCompra;
        this.recapadosMaximos = recapadosMaximos;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.baja = baja;
        this.notas = notas;
        this.acopladoId = acopladoId;
        this.camionId = camionId;
        this.proveedorId = proveedorId;
    }
}

