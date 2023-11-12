package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.DireccionModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DireccionDAO extends GenericDTO<DireccionModel> {
    List<DireccionModel> findAllByCiudadContainingIgnoreCase(String ciudad);
    List<DireccionModel> findAllByCiudadContainingIgnoreCaseAndEliminadaIsNull(String ciudad);
    List<DireccionModel> findAllByDireccionContainingIgnoreCase(String direccion);
    List<DireccionModel> findAllByDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);
    List<DireccionModel> findAllByNombreContainingIgnoreCase(String nombre);
    List<DireccionModel> findAllByNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);
    List<DireccionModel> findAllByNotasContainingIgnoreCase(String notas);
    List<DireccionModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);
}
