package gloit.hiperionida.helios.util.repository;

import gloit.hiperionida.helios.util.model.enums.RolEnum;
import gloit.hiperionida.helios.util.model.RolModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolDAO extends GenericDTO<RolModel> {
    Optional<RolModel> findByRol(RolEnum rol);
    Optional<RolModel> findByRolAndEliminadaIsNull(RolEnum rol);
}
