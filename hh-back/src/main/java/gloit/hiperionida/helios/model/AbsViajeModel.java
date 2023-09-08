package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsViajeModel extends AbsAuditoriaModel {
    private double cantidad;
    private double importe;
    private double kgNeto;
    private double kmCargado;
    private double kmVacio;
    @Column(columnDefinition = "TEXT")
    private String notas;

    @ManyToOne()
    private DireccionModel carga;

    @ManyToOne()
    private CategoriaViajeModel categoriaViaje;

    @ManyToOne()
    private ClienteModel comprador;

    @ManyToOne()
    private DireccionModel destino;

    @OneToOne
    private EventoModel fecha;

    @ManyToOne()
    private DireccionModel origen;

    @ManyToOne()
    private ClienteModel vendedor;
}

