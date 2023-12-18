package gloit.hiperionida.helios.util.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "logueo")
public class LogueoModel extends AbsAuditoriaModel {
    private String ip; // "190.183.122.8",
    private String hostname; // 190.183.122.8
    private String country_name; // "Argentina",
    private String state_prov; // "Buenos Aires Autonomous City",
    private String district; // "Villa Ortúzar",
    private String city; // "Buenos Aires City",
    private String zipcode; // "1427",
    private String country_flag; // "https://ipgeolocation.io/static/flags/ar_64.png",
    private String isp; // "CABLE VISIÃN CRESPO SRL",
    private String organization; // "Gigared S.A.",
    private String asn; // "AS20207",

    private String username;
    private Boolean logueado;

    public LogueoModel() {
    }

    public LogueoModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String ip, String hostname, String country_name, String state_prov, String district, String city, String zipcode, String country_flag, String isp, String organization, String asn, String username, Boolean logueado) {
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

