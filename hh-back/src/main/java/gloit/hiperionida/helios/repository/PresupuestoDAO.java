package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.PresupuestoModel;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PresupuestoDAO extends GenericDTO<PresupuestoModel> {
    List<PresupuestoModel> findAllByCantidadTransportadaBetween(Integer min, Integer max);
    List<PresupuestoModel> findAllByCantidadTransportadaBetweenAndEliminadaIsNull(Integer min, Integer max);
    List<PresupuestoModel> findAllByCategoriaViajeId(Long id);
    List<PresupuestoModel> findAllByCategoriaViajeIdAndEliminadaIsNull(Long id);
    List<PresupuestoModel> findAllByClienteId(Long id);
    List<PresupuestoModel> findAllByClienteIdAndEliminadaIsNull(Long id);
    List<PresupuestoModel> findAllByDestinoId(Long id);
    List<PresupuestoModel> findAllByDestinoIdAndEliminadaIsNull(Long id);
    @Query(value = "SELECT p FROM PresupuestoModel p " +
            "JOIN EventoModel e ON p.fechaId = e.id " +
            "where e.inicio between :inicio and :fin")
    List<PresupuestoModel> findAllByFechaFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    @Query(value = "SELECT p FROM PresupuestoModel p " +
            "JOIN EventoModel e ON p.fechaId = e.id " +
            "where e.inicio between :inicio and :fin and p.eliminada is null")
    List<PresupuestoModel> findAllByFechaFechaBetweenAndEliminadaIsNull(LocalDateTime inicio, LocalDateTime fin);
    List<PresupuestoModel> findAllByKmCargadoBetween(Double min, Double max);
    List<PresupuestoModel> findAllByKmCargadoBetweenAndEliminadaIsNull(Double min, Double max);
    List<PresupuestoModel> findAllByNotasContainingIgnoreCase(String nota);
    List<PresupuestoModel> findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(String nota);
    List<PresupuestoModel> findAllByOrigenId(Long id);
    List<PresupuestoModel> findAllByOrigenIdAndEliminadaIsNull(Long id);
    List<PresupuestoModel> findAllByValorKmBetween(Double min, Double max);
    List<PresupuestoModel> findAllByValorKmBetweenAndEliminadaIsNull(Double min, Double max);

    @Query(value = "SELECT p FROM PresupuestoModel p " +
            "JOIN ClienteModel c ON p.clienteId = c.id " +
            "where lower(c.nombre) like lower(concat('%',:nombre,'%')) ")
    List<PresupuestoModel> findAllByClienteNombreContainingIgnoreCase(String nombre);

    @Query(value = "SELECT p FROM PresupuestoModel p " +
            "JOIN ClienteModel c ON p.clienteId = c.id " +
            "where lower(c.nombre) like lower(concat('%',:nombre,'%')) and p.eliminada is null ")
    List<PresupuestoModel> findAllByClienteNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);

    @Query(value = "SELECT p FROM PresupuestoModel p " +
            "JOIN DireccionModel d ON p.origenId = d.id " +
            "where lower(d.ciudad) like lower(concat('%',:direccion,'%')) or lower(d.direccion) like lower(concat('%',:direccion,'%'))")
    List<PresupuestoModel> findAllByOrigenDireccionContainingIgnoreCase(String direccion);

    @Query(value = "SELECT p FROM PresupuestoModel p " +
            "JOIN DireccionModel d ON p.origenId = d.id " +
            "where lower(d.ciudad) like lower(concat('%',:direccion,'%')) or lower(d.direccion) like lower(concat('%',:direccion,'%')) and p.eliminada is null")
    List<PresupuestoModel> findAllByOrigenDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);

    @Query(value = "SELECT p FROM PresupuestoModel p " +
            "JOIN DireccionModel d ON p.destinoId = d.id " +
            "where lower(d.ciudad) like lower(concat('%',:direccion,'%')) or lower(d.direccion) like lower(concat('%',:direccion,'%'))")
    List<PresupuestoModel> findAllByDestinoDireccionContainingIgnoreCase(String direccion);

    @Query(value = "SELECT p FROM PresupuestoModel p " +
            "JOIN DireccionModel d ON p.destinoId = d.id " +
            "where lower(d.ciudad) like lower(concat('%',:direccion,'%')) or lower(d.direccion) like lower(concat('%',:direccion,'%')) and p.eliminada is null")
    List<PresupuestoModel> findAllByDestinoDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);

    @Query(value = "Select p FROM PresupuestoModel p " +
            "JOIN EventoModel e ON p.fechaId = e.id " +
            "where e.inicio >= :fecha order by e.inicio ASC limit 1")
    Optional<PresupuestoModel> findNextDate(LocalDateTime fecha);

}
