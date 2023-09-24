package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
public abstract class AbsViajeCreation extends AbsAuditoriaCreation {
    private String cantidadTransportada;
    private String valorKm;
    private String neto;
    private String tara;
    private String kmCargado;
    private String kmVacio;
    private String notas;
    private String categoriaViaje_id;
    private String origen_id;
    private String carga_id;
    private String destino_id;
    private String vendedor_id;
    private String intermediario_id;
    private String comprador_id;
    private String fecha_id;
}

