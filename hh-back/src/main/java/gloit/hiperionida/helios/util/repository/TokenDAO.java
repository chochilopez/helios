package gloit.hiperionida.helios.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import gloit.hiperionida.helios.util.model.TokenModel;

import java.util.List;
import java.util.Optional;

public interface TokenDAO extends JpaRepository<TokenModel, Long> {
	@Query(value = "select t from TokenModel t inner join UsuarioModel u on t.usuario_id = u.id where u.id = :id and (t.expirado = false or t.revocado = false)")
	List<TokenModel> findAllValidTokenByUser(Long id);

	Optional<TokenModel> findByToken(String token);
}
