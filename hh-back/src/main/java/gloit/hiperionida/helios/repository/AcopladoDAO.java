package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.AcopladoModel;
import gloit.hiperionida.helios.util.repository.GenericoDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcopladoDAO extends GenericoDAO<AcopladoModel> {
    List<AcopladoModel> findAllByAnio(Integer anio);
    List<AcopladoModel> findAllByAnioAndEliminadaIsNull(Integer anio);
    List<AcopladoModel> findAllByMarcaModeloContainingIgnoreCase(String marcaModelo);
    List<AcopladoModel> findAllByMarcaModeloContainingIgnoreCaseAndEliminadaIsNull(String marcaModelo);
    List<AcopladoModel> findAllByNotasContainingIgnoreCase(String notas);
    List<AcopladoModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);
    List<AcopladoModel> findAllByPatenteContainingIgnoreCase(String patente);
    List<AcopladoModel> findAllByPatenteContainingIgnoreCaseAndEliminadaIsNull(String patente);
}
