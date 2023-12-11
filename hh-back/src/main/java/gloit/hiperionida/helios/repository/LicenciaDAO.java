package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.LicenciaModel;
import gloit.hiperionida.helios.model.SeguroModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LicenciaDAO extends GenericDTO<LicenciaModel> {
    List<LicenciaModel> findAllByConductorId(Long id);
    List<LicenciaModel> findAllByConductorIdAndEliminadaIsNull(Long id);
    List<LicenciaModel> findAllByVencimientoId(Long id);
    List<LicenciaModel> findAllByVencimientoIdAndEliminadaIsNull(Long id);
    List<LicenciaModel> findAllByNotasContainingIgnoreCase(String notas);
    List<LicenciaModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);

    @Query(value = "SELECT l FROM LicenciaModel l " +
            "JOIN EventoModel e ON l.vencimientoId = e.id " +
            "where e.fecha between :inicio and :fin")
    List<LicenciaModel> findAllByVencimientoBetween(LocalDateTime inicio, LocalDateTime fin);

    @Query(value = "SELECT l FROM LicenciaModel l " +
            "JOIN EventoModel e ON l.vencimientoId = e.id " +
            "where e.fecha between :inicio and :fin and l.eliminada is null")
    List<LicenciaModel> findAllByVencimientoBetweenAndEliminadaIsNull(LocalDateTime inicio, LocalDateTime fin);
}
