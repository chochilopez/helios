package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.ClienteModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteDAO extends GenericDTO<ClienteModel> {
    List<ClienteModel> findAllByDireccionContainingIgnoreCase(String direccion);
    List<ClienteModel> findAllByDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);
    List<ClienteModel> findAllByEmailContainingIgnoreCase(String email);
    List<ClienteModel> findAllByEmailContainingIgnoreCaseAndEliminadaIsNull(String email);
    List<ClienteModel> findAllByIdentificacionContainingIgnoreCase(String identificacion);
    List<ClienteModel> findAllByIdentificacionContainingIgnoreCaseAndEliminadaIsNull(String identificacion);
    List<ClienteModel> findAllByNombreContainingIgnoreCase(String nombre);
    List<ClienteModel> findAllByNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);
    List<ClienteModel> findAllByTelefonoContainingIgnoreCase(String telefono);
    List<ClienteModel> findAllByTelefonoContainingIgnoreCaseAndEliminadaIsNull(String telefono);
    List<ClienteModel> findAllByNotasContainingIgnoreCase(String notas);
    List<ClienteModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);
}
