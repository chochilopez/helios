package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.CamionModel;
import gloit.hiperionida.helios.util.repository.GenericoDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CamionDAO extends GenericoDAO<CamionModel> {
    List<CamionModel> findAllByAnio(Integer anio);
    List<CamionModel> findAllByAnioAndEliminadaIsNull(Integer anio);
    List<CamionModel> findAllByMarcaModeloContainingIgnoreCase(String marcaModelo);
    List<CamionModel> findAllByMarcaModeloContainingIgnoreCaseAndEliminadaIsNull(String marcaModelo);
    List<CamionModel> findAllByNotasContainingIgnoreCase(String notas);
    List<CamionModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);
    List<CamionModel> findAllByPatenteContainingIgnoreCase(String patente);
    List<CamionModel> findAllByPatenteContainingIgnoreCaseAndEliminadaIsNull(String patente);

    List<CamionModel> findAllByNumeroChasisContainingIgnoreCase(String marcaModelo);
    List<CamionModel> findAllByNumeroChasisContainingIgnoreCaseAndEliminadaIsNull(String marcaModelo);
    List<CamionModel> findAllByNumeroMotorContainingIgnoreCase(String marcaModelo);
    List<CamionModel> findAllByNumeroMotorContainingIgnoreCaseAndEliminadaIsNull(String marcaModelo);
}
