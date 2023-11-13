package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.AcopladoModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcopladoDAO extends GenericDTO<AcopladoModel> {
    Optional<AcopladoModel> findBySeguroId(Long id);
    Optional<AcopladoModel> findBySeguroIdAndEliminadaIsNull(Long id);
    List<AcopladoModel> findAllByCantidadNeumaticosBetween(Integer min, Integer max);
    List<AcopladoModel> findAllByCantidadNeumaticosBetweenAndEliminadaIsNull(Integer min, Integer max);
    List<AcopladoModel> findAllByMarcaModeloContainingIgnoreCase(String marcaModelo);
    List<AcopladoModel> findAllByMarcaModeloContainingIgnoreCaseAndEliminadaIsNull(String marcaModelo);
    List<AcopladoModel> findAllByAnioBetween(Integer min, Integer max);
    List<AcopladoModel> findAllByAnioBetweenAndEliminadaIsNull(Integer min, Integer max);
    List<AcopladoModel> findAllByNotasContainingIgnoreCase(String notas);
    List<AcopladoModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);
}
