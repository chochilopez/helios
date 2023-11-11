package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.DireccionModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionDAO extends GenericDTO<DireccionModel> {
    List<DireccionModel> findAllByDireccionContainingIgnoreCase(String direccion);
    List<DireccionModel> findAllByDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);
    List<DireccionModel> findAllByCiudadContainingIgnoreCase(String ciudad);
    List<DireccionModel> findAllByCiudadContainingIgnoreCaseAndEliminadaIsNull(String ciudad);
    List<DireccionModel> findAllByNombreContainingIgnoreCase(String nombre);
    List<DireccionModel> findAllByNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);
    List<DireccionModel> findAllByNotasContainingIgnoreCase(String notas);
    List<DireccionModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);
}
