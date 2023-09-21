package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeDAO extends GenericDTO<ViajeModel> {
    List<ViajeModel> findAllByCamionId(Long id);
    List<ViajeModel> findAllByCamionIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByConductorId(Long id);
    List<ViajeModel> findAllByConductorIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByCompradorNombreContainingIgnoreCase(String nombre);
    List<ViajeModel> findAllByCompradorNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);
    List<ViajeModel> findAllByVendedorNombreContainingIgnoreCase(String nombre);
    List<ViajeModel> findAllByVendedorNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);
    List<ViajeModel> findAllByOrigenDireccionContainingIgnoreCase(String direccion);
    List<ViajeModel> findAllByOrigenDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);
    List<ViajeModel> findAllByDestinoDireccionContainingIgnoreCase(String direccion);
    List<ViajeModel> findAllByDestinoDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);
}
