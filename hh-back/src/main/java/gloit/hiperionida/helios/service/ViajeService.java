package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.ViajeCreation;
import gloit.hiperionida.helios.model.PresupuestoModel;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.time.LocalDateTime;
import java.util.List;

public interface ViajeService extends GenericService<ViajeModel, ViajeCreation> {
    List<ViajeModel> buscarTodasPorFechaEntreFechas(String inicio, String fin);
    List<ViajeModel> buscarTodasPorFechaEntreFechasConEliminadas(String inicio, String fin);

    List<ViajeModel> buscarTodasPorAcopladoId(Long id);
    List<ViajeModel> buscarTodasPorAcopladoIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorCamionId(Long id);
    List<ViajeModel> buscarTodasPorCamionIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorCargaId(Long id);
    List<ViajeModel> buscarTodasPorCargaIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorCategoriaViajeId(Long id);
    List<ViajeModel> buscarTodasPorCategoriaViajeIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorConductorId(Long id);
    List<ViajeModel> buscarTodasPorConductorIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorCompradorId(Long id);
    List<ViajeModel> buscarTodasPorCompradorIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorIntermediarioId(Long id);
    List<ViajeModel> buscarTodasPorIntermediarioIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorRangoKmCargado(Double min, Double max);
    List<ViajeModel> buscarTodasPorRangoKmCargadoConEliminadas(Double min, Double max);
    List<ViajeModel> buscarTodasPorRangoKmVacio(Double min, Double max);
    List<ViajeModel> buscarTodasPorRangoKmVacioConEliminadas(Double min, Double max);
    List<ViajeModel> buscarTodasPorNotas(String nota);
    List<ViajeModel> buscarTodasPorNotasConEliminadas(String nota);
    List<ViajeModel> buscarTodasPorGuia(String guia);
    List<ViajeModel> buscarTodasPorGuiaConEliminadas(String guia);
    List<ViajeModel> buscarTodasPorRangoCantidadTransportada(Integer min, Integer max);
    List<ViajeModel> buscarTodasPorRangoCantidadTransportadaConEliminadas(Integer min, Integer max);
    List<ViajeModel> buscarTodasPorRangoNeto(Double min, Double max);
    List<ViajeModel> buscarTodasPorRangoNetoConEliminadas(Double min, Double max);
    List<ViajeModel> buscarTodasPorRangoValorKm(Double min, Double max);
    List<ViajeModel> buscarTodasPorRangoValorKmConEliminadas(Double min, Double max);
    List<ViajeModel> buscarTodasPorVendedorId(Long id);
    List<ViajeModel> buscarTodasPorVendedorIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorOrigenId(Long id);
    List<ViajeModel> buscarTodasPorOrigenIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorDestinoId(Long id);
    List<ViajeModel> buscarTodasPorDestinoIdConEliminadas(Long id);

    List<ViajeModel> buscarTodasPorCompradorNombre(String nombre);
    List<ViajeModel> buscarTodasPorCompradorNombreConEliminadas(String nombre);
    List<ViajeModel> buscarTodasPorVendedorNombre(String nombre);
    List<ViajeModel> buscarTodasPorVendedorNombreConEliminadas(String nombre);
    List<ViajeModel> buscarTodasPorOrigenDireccion(String direccion);
    List<ViajeModel> buscarTodasPorOrigenDireccionConEliminadas(String direccion);
    List<ViajeModel> buscarTodasPorDestinoDireccion(String direccion);
    List<ViajeModel> buscarTodasPorDestinoDireccionConEliminadas(String direccion);

    Double sumarTodasCantidadTrasnportada();
    Double sumarTodasKmCargado();
    Double sumarTodasKmVacio();
    ViajeModel buscarUltimoViaje();
}

