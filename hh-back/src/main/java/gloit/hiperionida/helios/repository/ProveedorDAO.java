package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.ClienteModel;
import gloit.hiperionida.helios.model.ProveedorModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorDAO extends GenericDTO<ProveedorModel> {
    List<ProveedorModel> findAllByDireccionContainingIgnoreCase(String direccion);
    List<ProveedorModel> findAllByDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);
    List<ProveedorModel> findAllByEmailContainingIgnoreCase(String email);
    List<ProveedorModel> findAllByEmailContainingIgnoreCaseAndEliminadaIsNull(String email);
    List<ProveedorModel> findAllByIdentificacionContainingIgnoreCase(String identificacion);
    List<ProveedorModel> findAllByIdentificacionContainingIgnoreCaseAndEliminadaIsNull(String identificacion);
    List<ProveedorModel> findAllByNombreContainingIgnoreCase(String nombre);
    List<ProveedorModel> findAllByNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);
    List<ProveedorModel> findAllByTelefonoContainingIgnoreCase(String telefono);
    List<ProveedorModel> findAllByTelefonoContainingIgnoreCaseAndEliminadaIsNull(String telefono);
    List<ProveedorModel> findAllByNotasContainingIgnoreCase(String notas);
    List<ProveedorModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);
}
