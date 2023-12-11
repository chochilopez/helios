package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.PresupuestoModel;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ViajeDAO extends GenericDTO<ViajeModel> {
    List<ViajeModel> findAllByAcopladoId(Long id);
    List<ViajeModel> findAllByAcopladoIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByCamionId(Long id);
    List<ViajeModel> findAllByCamionIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByCantidadTransportadaBetween(Integer min, Integer max);
    List<ViajeModel> findAllByCantidadTransportadaBetweenAndEliminadaIsNull(Integer min, Integer max);
    List<ViajeModel> findAllByCargaId(Long id);
    List<ViajeModel> findAllByCargaIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByCategoriaViajeId(Long id);
    List<ViajeModel> findAllByCategoriaViajeIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByConductorId(Long id);
    List<ViajeModel> findAllByConductorIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByClienteId(Long id);
    List<ViajeModel> findAllByClienteIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByDestinoId(Long id);
    List<ViajeModel> findAllByDestinoIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByIntermediarioId(Long id);
    List<ViajeModel> findAllByIntermediarioIdAndEliminadaIsNull(Long id);
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
    List<ViajeModel> findAllByOrigenId(Long id);
    List<ViajeModel> findAllByOrigenIdAndEliminadaIsNull(Long id);

    @Query(value = "SELECT v FROM ViajeModel v " +
            "JOIN EventoModel e ON v.fechaId = e.id " +
            "where e.fecha between :inicio and :fin")
    List<ViajeModel> findAllByFechaFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    @Query(value = "SELECT v FROM ViajeModel v " +
            "JOIN EventoModel e ON v.fechaId = e.id " +
            "where e.fecha between :inicio and :fin and v.eliminada is null")
    List<ViajeModel> findAllByFechaFechaBetweenAndEliminadaIsNull(LocalDateTime inicio, LocalDateTime fin);

    @Query(value = "SELECT v FROM ViajeModel v " +
            "JOIN ClienteModel c ON v.clienteId = c.id " +
            "where lower(c.nombre) like lower(concat('%',:nombre,'%'))")
    List<ViajeModel> findAllByClienteNombreContainingIgnoreCase(String nombre);

    @Query(value = "SELECT v FROM ViajeModel v " +
            "JOIN ClienteModel c ON v.clienteId = c.id " +
            "where lower(c.nombre) like lower(concat('%',:nombre,'%')) and v.eliminada is null")
    List<ViajeModel> findAllByClienteNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);

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

    @Query(value = "Select v FROM ViajeModel v " +
            "JOIN EventoModel e ON v.fechaId = e.id " +
            "where e.fecha >= :fecha order by e.fecha ASC limit 1")
    Optional<ViajeModel> findNextDate(LocalDateTime fecha);
}