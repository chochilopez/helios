package gloit.hiperionida.helios.util.model;

import gloit.hiperionida.helios.util.model.enums.RolEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@SuperBuilder
@Table(name = "usuario")
public class UsuarioModel extends AbsAuditoriaModel implements UserDetails {
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

	public UsuarioModel() {
	}

	public UsuarioModel(Long id, Long creadorId, LocalDateTime creada, Long modificadorId, LocalDateTime modificada, Long eliminadorId, LocalDateTime eliminada, String nombre, String dni, String direccion, String telefono, Boolean habilitada, String username, String password, String token, Set<TokenModel> tokens, Set<RolModel> roles) {
		super(id, creadorId, creada, modificadorId, modificada, eliminadorId, eliminada);
		this.nombre = nombre;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.habilitada = habilitada;
		this.username = username;
		this.password = password;
		this.token = token;
		this.tokens = tokens;
		this.roles = roles;
	}
}