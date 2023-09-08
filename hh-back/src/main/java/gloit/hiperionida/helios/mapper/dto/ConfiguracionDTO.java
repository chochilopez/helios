package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import lombok.Setter;

import java.io.Serializable;

@Setter
public class ConfiguracionDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
    private String nombre;
    private String razonSocial;
    private String condicionIva;
    private String cuit;
    private String ingresosBrutos;
    private String inicioActividades;
    private String domicilio;
    private String localidad;
    private String email;
    private String telefono;
    private String cbu;
    private String alias;
}
