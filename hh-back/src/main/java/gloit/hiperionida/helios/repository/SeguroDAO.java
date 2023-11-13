package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.CamionModel;
import gloit.hiperionida.helios.model.SeguroModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeguroDAO extends GenericDTO<SeguroModel> {
    Optional<SeguroModel> findByAseguradoraId(Long id);
    Optional<SeguroModel> findByAseguradoraIdAndEliminadaIsNull(Long id);
    Optional<SeguroModel> findByVencimientoId(Long id);
    Optional<SeguroModel> findByVencimientoIdAndEliminadaIsNull(Long id);
    List<SeguroModel> findAllByNotasContainingIgnoreCase(String notas);
    List<SeguroModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);
}
