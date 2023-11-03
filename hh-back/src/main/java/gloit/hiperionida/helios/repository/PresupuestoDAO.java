package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.PresupuestoModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PresupuestoDAO extends GenericDTO<PresupuestoModel> {
    List<PresupuestoModel> findAllByCargaId(Long id);
    List<PresupuestoModel> findAllByCargaIdAndEliminadaIsNull(Long id);
    List<PresupuestoModel> findAllByCategoriaViajeId(Long id);
    List<PresupuestoModel> findAllByCategoriaViajeIdAndEliminadaIsNull(Long id);
    List<PresupuestoModel> findAllByCompradorId(Long id);
    List<PresupuestoModel> findAllByCompradorIdAndEliminadaIsNull(Long id);
    List<PresupuestoModel> findAllByFechaFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    List<PresupuestoModel> findAllByFechaFechaBetweenAndEliminadaIsNull(LocalDateTime inicio, LocalDateTime fin);
    List<PresupuestoModel> findAllByKmCargadoBetween(Double min, Double max);
    List<PresupuestoModel> findAllByKmCargadoBetweenAndEliminadaIsNull(Double min, Double max);
    List<PresupuestoModel> findAllByKmVacioBetween(Double min, Double max);
    List<PresupuestoModel> findAllByKmVacioBetweenAndEliminadaIsNull(Double min, Double max);
    List<PresupuestoModel> findAllByNotasContainingIgnoreCase(String nota);
    List<PresupuestoModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String nota);
    List<PresupuestoModel> findAllByValorKmBetween(Double min, Double max);
    List<PresupuestoModel> findAllByValorKmBetweenAndEliminadaIsNull(Double min, Double max);
    List<PresupuestoModel> findAllByVendedorId(Long id);
    List<PresupuestoModel> findAllByVendedorIdAndEliminadaIsNull(Long id);
    List<PresupuestoModel> findAllByOrigenId(Long id);
    List<PresupuestoModel> findAllByOrigenIdAndEliminadaIsNull(Long id);
    List<PresupuestoModel> findAllByDestinoId(Long id);
    List<PresupuestoModel> findAllByDestinoIdAndEliminadaIsNull(Long id);
    List<PresupuestoModel> findAllByCompradorNombreContainingIgnoreCase(String nombre);
    List<PresupuestoModel> findAllByCompradorNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);
    List<PresupuestoModel> findAllByVendedorNombreContainingIgnoreCase(String nombre);
    List<PresupuestoModel> findAllByVendedorNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);
    List<PresupuestoModel> findAllByOrigenDireccionContainingIgnoreCase(String direccion);
    List<PresupuestoModel> findAllByOrigenDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);
    List<PresupuestoModel> findAllByDestinoDireccionContainingIgnoreCase(String direccion);
    List<PresupuestoModel> findAllByDestinoDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);

    PresupuestoModel findFirstByOrderByIdDesc();
    List<PresupuestoModel> findAllByFechaFecha(LocalDateTime fecha);
    List<PresupuestoModel> findAllByFechaFechaAndEliminadaIsNull(LocalDateTime fecha);
}
