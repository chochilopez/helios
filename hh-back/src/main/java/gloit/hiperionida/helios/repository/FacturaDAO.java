package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.FacturaModel;
import gloit.hiperionida.helios.model.PresupuestoModel;
import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaDAO extends GenericDTO<FacturaModel> {
    Optional<FacturaModel> findByViajeId(Long id);
    Optional<FacturaModel> findByViajeIdAndEliminadaIsNull(Long id);
    Optional<FacturaModel> findByRemitoId(Long id);
    Optional<FacturaModel> findByRemitoIdAndEliminadaIsNull(Long id);
    List<FacturaModel> findAllByCodigoContainingIgnoreCase(String codigo);
    List<FacturaModel> findAllByCodigoContainingIgnoreCaseAndEliminadaIsNull(String codigo);
    List<FacturaModel> findAllByConceptoContainingIgnoreCase(String concepto);
    List<FacturaModel> findAllByConceptoContainingIgnoreCaseAndEliminadaIsNull(String concepto);
    List<FacturaModel> findAllByFechaEmisionBetween(LocalDateTime inicio, LocalDateTime fin);
    List<FacturaModel> findAllByFechaEmisionBetweenAndEliminadaIsNull(LocalDateTime inicio, LocalDateTime fin);
    @Query(value = "SELECT f FROM FacturaModel f " +
            "JOIN EventoModel e ON f.fechaVencimientoId = e.id " +
            "where e.fecha between :inicio and :fin")
    List<FacturaModel> findAllByFechaVencimientoBetween(LocalDateTime inicio, LocalDateTime fin);
    @Query(value = "SELECT f FROM FacturaModel f " +
            "JOIN EventoModel e ON f.fechaVencimientoId = e.id " +
            "where e.fecha between :inicio and :fin and f.eliminada is null")
    List<FacturaModel> findAllByFechaVencimientoBetweenAndEliminadaIsNull(LocalDateTime inicio, LocalDateTime fin);
    List<FacturaModel> findAllByNotasContainingIgnoreCase(String notas);
    List<FacturaModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String notas);
    List<FacturaModel> findAllByNumeroComprobanteContainingIgnoreCase(String numeroComprobante);
    List<FacturaModel> findAllByNumeroComprobanteContainingIgnoreCaseAndEliminadaIsNull(String numeroComprobante);
    List<FacturaModel> findAllByPagada(Boolean pagada);
    List<FacturaModel> findAllByPagadaAndEliminadaIsNull(Boolean pagada);
    List<FacturaModel> findAllByRazonSocialContainingIgnoreCase(String razonSocial);
    List<FacturaModel> findAllByRazonSocialContainingIgnoreCaseAndEliminadaIsNull(String razonSocial);
    List<FacturaModel> findAllByTipoComprobante(TipoComprobanteEnum tipoComprobante);
    List<FacturaModel> findAllByTipoComprobanteAndEliminadaIsNull(TipoComprobanteEnum tipoComprobante);
}
