package gloit.hiperionida.helios.util.model;

import gloit.hiperionida.helios.util.model.enums.TipoToken;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "token")
public class TokenModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@Column(unique = true, columnDefinition = "TEXT")
	public String token;

	@Enumerated(EnumType.STRING)
	public TipoToken tipoToken = TipoToken.BEARER;

	public boolean revocado;

	public boolean expirado;

	@ManyToOne()
	@JoinColumn(name = "usuario_id")
	private UsuarioModel usuario;
}
