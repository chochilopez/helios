package gloit.hiperionida.helios.util.security;

import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.model.enums.RolEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
	@Value("${neso.app.jwtSecret}")
	private String secretKey;
	@Value("${neso.app.jwtExpiration}")
	private long jwtExpiration;

    public String extraerUsuario(String token) {
        return extraerClaims(token, Claims::getSubject);
    }

    public <T> T extraerClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extraerTodasClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generarToken(UsuarioModel userDetails) {
        return construirToken(userDetails, jwtExpiration);
    }

    private String construirToken(UsuarioModel userDetails, long expiration) {
		Map<String, Object> claims = new HashMap<>();
		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
		if (roles.contains(new SimpleGrantedAuthority(RolEnum.CARGA.name())))
			claims.put("CARGA", true);
		if (roles.contains(new SimpleGrantedAuthority(RolEnum.USUARIO.name())))
			claims.put("USUARIO", true);
		if (roles.contains(new SimpleGrantedAuthority(RolEnum.ADMIN.name())))
			claims.put("ADMIN", true);

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getLlaveFirma(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean esValidoToken(String token, UserDetails userDetails) {
		try {
			final String username = extraerUsuario(token);
			return (username.equals(userDetails.getUsername())) && !esExpiradoToken(token);
		} catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			throw ex;
		}
    }

    private boolean esExpiradoToken(String token) {
        return extraerExpiracion(token).before(new Date());
    }

    private Date extraerExpiracion(String token) {
        return extraerClaims(token, Claims::getExpiration);
    }

    private Claims extraerTodasClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getLlaveFirma())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getLlaveFirma() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

	public List<GrantedAuthority> getRolesFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		List<GrantedAuthority> roles=new ArrayList<GrantedAuthority>();

		Boolean carga = claims.get("CARGA", Boolean.class);
		Boolean usuario = claims.get("USUARIO", Boolean.class);
		Boolean admin = claims.get("ADMIN", Boolean.class);

		if (carga != null && carga)
			roles.add(new SimpleGrantedAuthority(RolEnum.CARGA.name()));
		if (usuario != null && usuario)
			roles.add(new SimpleGrantedAuthority(RolEnum.USUARIO.name()));
		if (admin != null && admin)
			roles.add(new SimpleGrantedAuthority(RolEnum.ADMIN.name()));

		return roles;
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
}
