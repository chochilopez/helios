package gloit.hiperionida.helios.util.model;

import gloit.hiperionida.helios.util.model.enums.RolEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "usuario")
public class UsuarioModel extends AbsAuditoriaModel implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String dni;
	private String direccion;
	private String telefono;
	private Boolean habilitada;
	private String username;
	private String password;
	private String token;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	private Set<TokenModel> tokens;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(  name = "rol_usuario", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<RolModel> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRol().name()))
				.collect(Collectors.toList());
	}

	public List<RolEnum> getListadoRoles() {
		List<RolEnum> roles = new ArrayList<>();
		for (RolModel rol:this.roles) {
			roles.add(rol.getRol());
		}
		return roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}