package gloit.hiperionida.helios.util.mapper.creation;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class LogueoCreation extends AbsAuditoriaCreation {
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
    private String logueado;

    public LogueoCreation() {
    }

    public LogueoCreation(String id, String creadorId, String creada, String eliminadorId, String eliminada, String modificadorId, String modificada, String ip, String hostname, String country_name, String state_prov, String district, String city, String zipcode, String country_flag, String isp, String organization, String asn, String username, String logueado) {
        super(id, creadorId, creada, eliminadorId, eliminada, modificadorId, modificada);
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
