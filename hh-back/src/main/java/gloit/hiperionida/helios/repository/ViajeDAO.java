package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViajeDAO extends GenericDTO<ViajeModel> {
    List<ViajeModel> findAllByCamionId(Long id);
    List<ViajeModel> findAllByCamionIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByConductorId(Long id);
    List<ViajeModel> findAllByConductorIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByCompradorId(Long id);
    List<ViajeModel> findAllByCompradorIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByVendedorId(Long id);
    List<ViajeModel> findAllByVendedorIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByOrigenId(Long id);
    List<ViajeModel> findAllByOrigenIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByDestinoId(Long id);
    List<ViajeModel> findAllByDestinoIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByCompradorNombreContainingIgnoreCase(String nombre);
    List<ViajeModel> findAllByCompradorNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);
    List<ViajeModel> findAllByVendedorNombreContainingIgnoreCase(String nombre);
    List<ViajeModel> findAllByVendedorNombreContainingIgnoreCaseAndEliminadaIsNull(String nombre);
    List<ViajeModel> findAllByOrigenDireccionContainingIgnoreCase(String direccion);
    List<ViajeModel> findAllByOrigenDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);
    List<ViajeModel> findAllByDestinoDireccionContainingIgnoreCase(String direccion);
    List<ViajeModel> findAllByDestinoDireccionContainingIgnoreCaseAndEliminadaIsNull(String direccion);

    @Query("select SUM (cantidadTransportada) from ViajeModel")
    Double sumarCantidadTrasnportada();
    @Query("select SUM (kmCargado) from ViajeModel")
    Double sumarKmCargado();
    @Query("select SUM (kmVacio) from ViajeModel")
    Double sumarKmVacio();

    ViajeModel findFirstByOrderByIdDesc();


}
//    this.cantidadTransportada = ''
//    this.valorKm = ''
//    this.kgNeto = ''
//    this.kmCargado = ''
//    this.kmVacio = ''
//    this.notas = ''
//    this.guia = ''