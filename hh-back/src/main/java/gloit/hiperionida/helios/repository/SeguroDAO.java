package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.SeguroModel;
import gloit.hiperionida.helios.util.repository.GenericoDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SeguroDAO extends GenericoDAO<SeguroModel> {
    List<SeguroModel> findAllByAcopladoId(Long id);
    List<SeguroModel> findAllByAcopladoIdAndEliminadaIsNull(Long id);
    List<SeguroModel> findAllByAseguradoraId(Long id);
    List<SeguroModel> findAllByAseguradoraIdAndEliminadaIsNull(Long id);
    List<SeguroModel> findAllByCamionId(Long id);
    List<SeguroModel> findAllByCamionIdAndEliminadaIsNull(Long id);
    List<SeguroModel> findAllByVencimientoId(Long id);
    List<SeguroModel> findAllByVencimientoIdAndEliminadaIsNull(Long id);
    List<SeguroModel> findAllByNotasContainingIgnoreCase(String notas);
    List<SeguroModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);

    @Query(value = "SELECT s FROM SeguroModel s " +
            "JOIN EventoModel e ON s.vencimientoId = e.id " +
            "where e.fecha between :inicio and :fin")
    List<SeguroModel> findAllByVencimientoBetween(LocalDateTime inicio, LocalDateTime fin);
    @Query(value = "SELECT s FROM SeguroModel s " +
            "JOIN EventoModel e ON s.vencimientoId = e.id " +
            "where e.fecha between :inicio and :fin and s.eliminada is null")
    List<SeguroModel> findAllByVencimientoBetweenAndEliminadaIsNull(LocalDateTime inicio, LocalDateTime fin);
}
