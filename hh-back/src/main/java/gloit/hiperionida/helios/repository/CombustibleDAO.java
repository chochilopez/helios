package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.CombustibleModel;
import gloit.hiperionida.helios.model.FacturaModel;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CombustibleDAO extends GenericDTO<CombustibleModel> {
    List<CombustibleModel> findAllByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    List<CombustibleModel> findAllByFechaBetweenAndEliminadaIsNull(LocalDateTime inicio, LocalDateTime fin);
    List<CombustibleModel> findAllByCamionId(Long id);
    List<CombustibleModel> findAllByCamionIdAndEliminadaIsNull(Long id);
    List<CombustibleModel> findAllByConductorId(Long id);
    List<CombustibleModel> findAllByConductorIdAndEliminadaIsNull(Long id);
    List<CombustibleModel> findAllByProveedorId(Long id);
    List<CombustibleModel> findAllByProveedorIdAndEliminadaIsNull(Long id);
    List<CombustibleModel> findAllByNotasContainingIgnoreCase(String notas);
    List<CombustibleModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);
}
