package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "ingreso")
public class IngresoModel extends AbsAuditoriaModel {
    private String ip;
    private String hostname;
    private String country_name;
    private String state_prov;
    private String district;
    private String city;
    private String zipcode;
    private String country_flag;
    private String isp;
    private String organization;
    private String asn;
    private String username;
    private Boolean logueado;

    public IngresoModel() {
    }

    public IngresoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String ip, String hostname, String country_name, String state_prov, String district, String city, String zipcode, String country_flag, String isp, String organization, String asn, String username, Boolean logueado) {
        super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
        this.ip = ip;
        this.hostname = hostname;
        this.country_name = country_name;
        this.state_prov = state_prov;
        this.district = district;
        this.city = city;
        this.zipcode = zipcode;
        this.country_flag = country_flag;
        this.isp = isp;
        this.organization = organization;
        this.asn = asn;
        this.username = username;
        this.logueado = logueado;
    }
}
