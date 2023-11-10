package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.PresupuestoModel;
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
    List<ViajeModel> findAllByCantidadTransportadaBetween(Integer min, Integer max);
    List<ViajeModel> findAllByCantidadTransportadaBetweenAndEliminadaIsNull(Integer min, Integer max);
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

    @Query(value = "SELECT v FROM ViajeModel v " +
            "JOIN EventoModel e ON v.fechaId = e.id " +
            "where e.fecha between :inicio and :fin")
    List<ViajeModel> findAllByFechaFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    @Query(value = "SELECT v FROM ViajeModel v " +
            "JOIN EventoModel e ON v.fechaId = e.id " +
            "where e.fecha between :inicio and :fin and v.eliminada is null")
    List<ViajeModel> findAllByFechaFechaBetweenAndEliminadaIsNull(LocalDateTime inicio, LocalDateTime fin);

    @Query(value = "SELECT v FROM ViajeModel v " +
            "JOIN ClienteModel c ON v.compradorId = c.id " +
            "where lower(c.nombre) like lower(concat('%',:nombre,'%'))")
    List<ViajeModel> findAllByCompradorNombreContainingIgnoreCase(String nombre);

    @Query(value = "SELECT v FROM ViajeModel v " +
            "JOIN ClienteModel c ON v.compradorId = c.id " +
            "where lower(c.nombre) like lower(concat('%',:nombre,'%')) and v.eliminada is null")
    List<ViajeModel> findAllByCompradorNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);

    @Query(value = "SELECT v FROM ViajeModel v " +
            "JOIN ClienteModel c ON v.vendedorId = c.id " +
            "where lower(c.nombre) like lower(concat('%',:nombre,'%'))")
    List<ViajeModel> findAllByVendedorNombreContainingIgnoreCase(String nombre);

    @Query(value = "SELECT v FROM ViajeModel v " +
            "JOIN ClienteModel c ON v.vendedorId = c.id " +
            "where lower(c.nombre) like lower(concat('%',:nombre,'%')) and v.eliminada is null")
    List<ViajeModel> findAllByVendedorNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);

    @Query(value = "SELECT v FROM ViajeModel v " +
            "JOIN DireccionModel d ON v.origenId = d.id " +
            "where lower(d.ciudad) like lower(concat('%',:direccion,'%')) or lower(d.direccion) like lower(concat('%',:direccion,'%'))")
    List<ViajeModel> findAllByOrigenDireccionContainingIgnoreCase(String direccion);

    @Query(value = "SELECT v FROM ViajeModel v " +
            "JOIN DireccionModel d ON v.origenId = d.id " +
            "where lower(d.ciudad) like lower(concat('%',:direccion,'%')) or lower(d.direccion) like lower(concat('%',:direccion,'%')) and v.eliminada is null")
    List<ViajeModel> findAllByOrigenDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);

    @Query(value = "SELECT v FROM ViajeModel v " +
            "JOIN DireccionModel d ON v.destinoId = d.id " +
            "where lower(d.ciudad) like lower(concat('%',:direccion,'%')) or lower(d.direccion) like lower(concat('%',:direccion,'%'))")
    List<ViajeModel> findAllByDestinoDireccionContainingIgnoreCase(String direccion);

    @Query(value = "SELECT v FROM ViajeModel v " +
            "JOIN DireccionModel d ON v.destinoId = d.id " +
            "where lower(d.ciudad) like lower(concat('%',:direccion,'%')) or lower(d.direccion) like lower(concat('%',:direccion,'%')) and v.eliminada is null")
    List<ViajeModel> findAllByDestinoDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);

    @Query("select SUM (cantidadTransportada) from ViajeModel")
    Double sumarCantidadTrasnportada();
    @Query("select SUM (kmCargado) from ViajeModel")
    Double sumarKmCargado();
    @Query("select SUM (kmVacio) from ViajeModel")
    Double sumarKmVacio();
}