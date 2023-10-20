package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ViajeDAO extends GenericDTO<ViajeModel> {
    List<ViajeModel> findAllByFechaFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByFechaFechaBetweenAndEliminadaIsNull(LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByCamionId(Long id);
    List<ViajeModel> findAllByCamionIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByCamionIdAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByCamionIdAndEliminadaIsNullAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByConductorId(Long id);
    List<ViajeModel> findAllByConductorIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByConductorIdAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByConductorIdAndEliminadaIsNullAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByCompradorId(Long id);
    List<ViajeModel> findAllByCompradorIdAndEliminadaIsNull(Long id);
    List<ViajeModel> findAllByCompradorIdAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
    List<ViajeModel> findAllByCompradorIdAndEliminadaIsNullAndCreadaBetween(Long id, LocalDateTime inicio, LocalDateTime fin);
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