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

    @ManyToOne()
    private CategoriaViajeModel categoriaViaje;

    @ManyToOne()
    private DireccionModel origen;

    @ManyToOne()
    private DireccionModel carga;

    @ManyToOne()
    private DireccionModel destino;

    @ManyToOne()
    private ClienteModel vendedor;

    @ManyToOne()
    private ClienteModel intermediario;

    @ManyToOne()
    private ClienteModel comprador;

    @OneToOne
    private EventoModel fecha;
}

