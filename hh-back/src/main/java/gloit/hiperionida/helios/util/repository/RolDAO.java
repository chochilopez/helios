package gloit.hiperionida.helios.util.repository;

import gloit.hiperionida.helios.util.model.enums.RolEnum;
import gloit.hiperionida.helios.util.model.RolModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolDAO extends JpaRepository<RolModel, Long> {
    Optional<RolModel> findByRol(RolEnum rol);
}
