package gloit.hiperionida.helios.mapper.dto;

import gloit.hiperionida.helios.util.mapper.dto.AbsAuditoriaDTO;
import gloit.hiperionida.helios.util.mapper.dto.UsuarioDTO;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class VisitaDTO extends AbsAuditoriaDTO implements Serializable {
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
    private String asn; //
    private UsuarioDTO visitante;
}
