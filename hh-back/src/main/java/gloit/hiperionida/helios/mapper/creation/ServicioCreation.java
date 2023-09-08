package gloit.hiperionida.helios.mapper.creation;

import lombok.Getter;

@Getter
public class ServicioCreation extends AbsVehiculoCreation {
    private String id;
    private String kmServicio;
    private String kmActual;
    private String kmProximo;
    private String tipo;
    private String camion_id;
}
