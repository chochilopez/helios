package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class VisitaCreation extends AbsAuditoriaCreation {
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
    private String visitante_id;
}
