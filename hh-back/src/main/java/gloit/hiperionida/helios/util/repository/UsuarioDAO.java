package gloit.hiperionida.helios.util.repository;

import gloit.hiperionida.helios.util.model.UsuarioModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDAO extends GenericoDAO<UsuarioModel> {
  Optional<UsuarioModel> findByUsernameContainingIgnoreCase(String username);
  Optional<UsuarioModel> findByUsernameContainingIgnoreCaseAndEliminadaIsNull(String username);
  Optional<UsuarioModel> findByUsernameContainingIgnoreCaseAndHabilitadaIsTrueAndEliminadaIsNull(String username);
  Boolean existsByUsername(String username);
  Boolean existsByUsernameContainingIgnoreCase(String username);

  Optional<UsuarioModel> findByTokenAndEliminadaIsNullAndHabilitadaIsFalse(String username);
}
