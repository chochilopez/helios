package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.FacturaModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturaDAO extends GenericDTO<FacturaModel> {
    Optional<FacturaModel> findByViajeId(Long id);
    Optional<FacturaModel> findByViajeIdAndEliminadaIsNull(Long id);
}
