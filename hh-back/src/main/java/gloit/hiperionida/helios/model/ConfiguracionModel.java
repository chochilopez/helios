package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.model.enums.CondicionIvaEnum;
import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "configuracion")
public class ConfiguracionModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String razonSocial;
    @Enumerated(EnumType.STRING)
    private CondicionIvaEnum condicionIva;
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