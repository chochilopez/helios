package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import gloit.hiperionida.helios.util.mapper.dto.UsuarioDTO;
import lombok.Setter;

import java.io.Serializable;

@Setter
public class VisitaDTO extends AbsAuditoriaDTO implements Serializable {
    private String id;
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
    private UsuarioDTO visitante;
}
