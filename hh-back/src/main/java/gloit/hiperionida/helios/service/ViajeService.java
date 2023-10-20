package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.ViajeCreation;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.time.LocalDateTime;
import java.util.List;

public interface ViajeService extends GenericService<ViajeModel, ViajeCreation> {
    List<ViajeModel> buscarTodasPorFechaEntreFechas(String inicio, String fin);
    List<ViajeModel> buscarTodasPorFechaEntreFechasConEliminadas(String inicio, String fin);

    List<ViajeModel> buscarTodasPorCamionId(Long id);
    List<ViajeModel> buscarTodasPorCamionIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorCamionIdEntreFechas(Long id, String inicio, String fin);
    List<ViajeModel> buscarTodasPorCamionIdEntreFechasConEliminadas(Long id, String inicio, String fin);
    List<ViajeModel> buscarTodasPorChoferId(Long id);
    List<ViajeModel> buscarTodasPorChoferIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorChoferIdEntreFechas(Long id, String inicio, String fin);
    List<ViajeModel> buscarTodasPorChoferIdEntreFechasConEliminadas(Long id, String inicio, String fin);
    List<ViajeModel> buscarTodasPorCompradorId(Long id);
    List<ViajeModel> buscarTodasPorCompradorIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorCompradorIdEntreFechas(Long id, String inicio, String fin);
    List<ViajeModel> buscarTodasPorCompradorIdEntreFechasConEliminadas(Long id, String inicio, String fin);
    List<ViajeModel> buscarTodasPorVendedorId(Long id);
    List<ViajeModel> buscarTodasPorVendedorIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorVendedorIdEntreFechas(Long id, String inicio, String fin);
    List<ViajeModel> buscarTodasPorVendedorIdEntreFechasConEliminadas(Long id, String inicio, String fin);
    List<ViajeModel> buscarTodasPorOrigenId(Long id);
    List<ViajeModel> buscarTodasPorOrigenIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorOrigenIdEntreFechas(Long id, String inicio, String fin);
    List<ViajeModel> buscarTodasPorOrigenIdEntreFechasConEliminadas(Long id, String inicio, String fin);
    List<ViajeModel> buscarTodasPorDestinoId(Long id);
    List<ViajeModel> buscarTodasPorDestinoIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorDestinoIdEntreFechas(Long id, String inicio, String fin);
    List<ViajeModel> buscarTodasPorDestinoIdEntreFechasConEliminadas(Long id, String inicio, String fin);

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

