package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.Getter;

@Getter
public class ConfiguracionCreation extends AbsAuditoriaCreation {
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
