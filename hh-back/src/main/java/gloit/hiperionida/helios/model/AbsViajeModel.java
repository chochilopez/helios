package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsViajeModel extends AbsAuditoriaModel {
    private Double cantidadTransportada;
    private Double valorKm;
    private Double neto;
    private Double kmCargado;
    private Double kmVacio;
    @Column(columnDefinition = "TEXT")
    private String notas;

    private Long categoriaViaje_id;
    private Long origen_id;
    private Long carga_id;
    private Long destino_id;
    private Long vendedor_id;
    private Long intermediario_id;
    private Long comprador_id;
    private Long fecha_id;
}

