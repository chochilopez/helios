package gloit.hiperionida.helios.util.mapper.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LogueoDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
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
}
