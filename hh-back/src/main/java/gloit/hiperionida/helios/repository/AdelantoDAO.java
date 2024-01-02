package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.AdelantoModel;
import gloit.hiperionida.helios.util.repository.GenericoDAO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdelantoDAO extends GenericoDAO<AdelantoModel> {
    List<AdelantoModel> findAllByCajaId(Long id);
    List<AdelantoModel> findAllByCajaIdAndEliminadaIsNull(Long id);
    List<AdelantoModel> findAllByConductorId(Long id);
    List<AdelantoModel> findAllByConductorIdAndEliminadaIsNull(Long id);
    List<AdelantoModel> findAllByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    List<AdelantoModel> findAllByFechaBetweenAndEliminadaIsNull(LocalDateTime inicio, LocalDateTime fin);
    List<AdelantoModel> findAllByMontoBetween(Double min, Double max);
    List<AdelantoModel> findAllByMontoBetweenAndEliminadaIsNull(Double min, Double max);
    List<AdelantoModel> findAllByNotasContainingIgnoreCase(String notas);
    List<AdelantoModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);
}
