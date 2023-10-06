package gloit.hiperionida.helios.model;

import gloit.hiperionida.helios.util.model.AbsAuditoriaModel;
import gloit.hiperionida.helios.util.model.UsuarioModel;

import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "visita")
public class VisitaModel extends AbsAuditoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @ManyToOne()
    private UsuarioModel visitante;
}

