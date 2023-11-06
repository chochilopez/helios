package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ViajeDAO extends GenericDTO<ViajeModel> {
    List<ViajeModel> findAllByAcopladoId(Long id);
    List<ViajeModel> findAllByAcopladoIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByAcopladoIdAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByAcopladoIdAndEliminadaIsNullAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByCamionId(Long id);
    List<ViajeModel> findAllByCamionIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByCamionIdAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByCamionIdAndEliminadaIsNullAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByCargaId(Long id);
    List<ViajeModel> findAllByCargaIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByCargaIdAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByCargaIdAndEliminadaIsNullAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByCategoriaViajeId(Long id);
    List<ViajeModel> findAllByCategoriaViajeIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByCategoriaViajeIdAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByCategoriaViajeIdAndEliminadaIsNullAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByConductorId(Long id);
    List<ViajeModel> findAllByConductorIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByConductorIdAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByConductorIdAndEliminadaIsNullAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByCompradorId(Long id);
    List<ViajeModel> findAllByCompradorIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByCompradorIdAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByCompradorIdAndEliminadaIsNullAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByIntermediarioId(Long id);
    List<ViajeModel> findAllByIntermediarioIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByIntermediarioIdAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByIntermediarioIdAndEliminadaIsNullAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByKmCargadoBetween(Double min, Double max);
    List<ViajeModel> findAllByKmCargadoBetweenAndEliminadaIsNull(Double min, Double max);
    List<ViajeModel> findAllByKmVacioBetween(Double min, Double max);
    List<ViajeModel> findAllByKmVacioBetweenAndEliminadaIsNull(Double min, Double max);
    List<ViajeModel> findAllByNotasContainingIgnoreCase(String nota);
    List<ViajeModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String nota);
    List<ViajeModel> findAllByGuiaContainingIgnoreCase(String nota);
    List<ViajeModel> findAllByGuiaContainingIgnoreCaseAndEliminadaIsNull(String nota);
    List<ViajeModel> findAllByNetoBetween(Double min, Double max);
    List<ViajeModel> findAllByNetoBetweenAndEliminadaIsNull(Double min, Double max);
    List<ViajeModel> findAllByValorKmBetween(Double min, Double max);
    List<ViajeModel> findAllByValorKmBetweenAndEliminadaIsNull(Double min, Double max);
    List<ViajeModel> findAllByVendedorId(Long id);
    List<ViajeModel> findAllByVendedorIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByVendedorIdAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByVendedorIdAndEliminadaIsNullAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByOrigenId(Long id);
    List<ViajeModel> findAllByOrigenIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByOrigenIdAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByOrigenIdAndEliminadaIsNullAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByDestinoId(Long id);
    List<ViajeModel> findAllByDestinoIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByDestinoIdAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByDestinoIdAndEliminadaIsNullAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);

    @Query(value = "SELECT * FROM presupuesto p", nativeQuery = true)
    List<ViajeModel> findAllByFechaFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    @Query(value = "SELECT * FROM presupuesto p", nativeQuery = true)
    List<ViajeModel> findAllByFechaFechaBetweenAndEliminadaIsNull(LocalDateTime inicio, LocalDateTime fin);
    @Query(value = "SELECT * FROM presupuesto p", nativeQuery = true)
    List<ViajeModel> findAllByCompradorNombreContainingIgnoreCase(String nombre);
    @Query(value = "SELECT * FROM presupuesto p", nativeQuery = true)
    List<ViajeModel> findAllByCompradorNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);
    @Query(value = "SELECT * FROM presupuesto p", nativeQuery = true)
    List<ViajeModel> findAllByVendedorNombreContainingIgnoreCase(String nombre);
    @Query(value = "SELECT * FROM presupuesto p", nativeQuery = true)
    List<ViajeModel> findAllByVendedorNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);
    @Query(value = "SELECT * FROM presupuesto p", nativeQuery = true)
    List<ViajeModel> findAllByOrigenDireccionContainingIgnoreCase(String direccion);
    @Query(value = "SELECT * FROM presupuesto p", nativeQuery = true)
    List<ViajeModel> findAllByOrigenDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);
    @Query(value = "SELECT * FROM presupuesto p", nativeQuery = true)
    List<ViajeModel> findAllByDestinoDireccionContainingIgnoreCase(String direccion);
    @Query(value = "SELECT * FROM presupuesto p", nativeQuery = true)
    List<ViajeModel> findAllByDestinoDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);

    @Query("select SUM (cantidadTransportada) from ViajeModel")
    Double sumarCantidadTrasnportada();
    @Query("select SUM (kmCargado) from ViajeModel")
    Double sumarKmCargado();
    @Query("select SUM (kmVacio) from ViajeModel")
    Double sumarKmVacio();
}