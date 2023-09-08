package gloit.hiperionida.helios.mapper.dto;

import lombok.Setter;

import java.io.Serializable;

@Setter
public class PresupuestoDTO extends AbsViajeDTO implements Serializable {
    private String id;
    private String validez;
}
