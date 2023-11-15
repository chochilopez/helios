package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.ConductorModel;
import gloit.hiperionida.helios.model.ConductorModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConductorDAO extends GenericDTO<ConductorModel> {
    List<ConductorModel> findAllByDireccionContainingIgnoreCase(String direccion);
    List<ConductorModel> findAllByDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);
    List<ConductorModel> findAllByEmailContainingIgnoreCase(String email);
    List<ConductorModel> findAllByEmailContainingIgnoreCaseAndEliminadaIsNull(String email);
    List<ConductorModel> findAllByIdentificacionContainingIgnoreCase(String identificacion);
    List<ConductorModel> findAllByIdentificacionContainingIgnoreCaseAndEliminadaIsNull(String identificacion);
    List<ConductorModel> findAllByNombreContainingIgnoreCase(String nombre);
    List<ConductorModel> findAllByNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);
    List<ConductorModel> findAllByTelefonoContainingIgnoreCase(String telefono);
    List<ConductorModel> findAllByTelefonoContainingIgnoreCaseAndEliminadaIsNull(String telefono);
    List<ConductorModel> findAllByNotasContainingIgnoreCase(String notas);
    List<ConductorModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);
}
