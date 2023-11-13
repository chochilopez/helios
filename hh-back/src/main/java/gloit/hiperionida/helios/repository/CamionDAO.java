package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.AcopladoModel;
import gloit.hiperionida.helios.model.CamionModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CamionDAO extends GenericDTO<CamionModel> {
    Optional<CamionModel> findBySeguroId(Long id);
    Optional<CamionModel> findBySeguroIdAndEliminadaIsNull(Long id);
    List<CamionModel> findAllByCantidadNeumaticosBetween(Integer min, Integer max);
    List<CamionModel> findAllByCantidadNeumaticosBetweenAndEliminadaIsNull(Integer min, Integer max);
    List<CamionModel> findAllByMarcaModeloContainingIgnoreCase(String marcaModelo);
    List<CamionModel> findAllByMarcaModeloContainingIgnoreCaseAndEliminadaIsNull(String marcaModelo);
    List<CamionModel> findAllByAnioBetween(Integer min, Integer max);
    List<CamionModel> findAllByAnioBetweenAndEliminadaIsNull(Integer min, Integer max);
    List<CamionModel> findAllByNotasContainingIgnoreCase(String notas);
    List<CamionModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);

    List<CamionModel> findAllByNumeroChasisContainingIgnoreCase(String marcaModelo);
    List<CamionModel> findAllByNumeroChasisContainingIgnoreCaseAndEliminadaIsNull(String marcaModelo);
    List<CamionModel> findAllByNumeroMotorContainingIgnoreCase(String marcaModelo);
    List<CamionModel> findAllByNumeroMotorContainingIgnoreCaseAndEliminadaIsNull(String marcaModelo);
}
