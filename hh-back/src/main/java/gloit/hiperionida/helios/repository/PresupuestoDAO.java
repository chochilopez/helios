package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.PresupuestoModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PresupuestoDAO extends GenericDTO<PresupuestoModel> {
    List<PresupuestoModel> findAllByCompradorNombreContainingIgnoreCase(String nombre);
    List<PresupuestoModel> findAllByCompradorNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);
    List<PresupuestoModel> findAllByOrigenDireccionContainingIgnoreCase(String direccion);
    List<PresupuestoModel> findAllByOrigenDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);
    List<PresupuestoModel> findAllByDestinoDireccionContainingIgnoreCase(String direccion);
    List<PresupuestoModel> findAllByDestinoDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);

    List<PresupuestoModel> findAllByFechaFecha(LocalDateTime fecha);
    List<PresupuestoModel> findAllByFechaFechaAndEliminadaIsNull(LocalDateTime fecha);

    PresupuestoModel findFirstByOrderByIdDesc();
}
