package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.ViajeCreation;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface ViajeService extends GenericService<ViajeModel, ViajeCreation> {
    List<ViajeModel> buscarTodasPorCamionId(Long id);
    List<ViajeModel> buscarTodasPorCamionIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorChoferId(Long id);
    List<ViajeModel> buscarTodasPorChoferIdConEliminadas(Long id);
    List<ViajeModel> buscarTodasPorCompradorNombre(String nombre);
    List<ViajeModel> buscarTodasPorCompradorNombreConEliminadas(String nombre);
    List<ViajeModel> buscarTodasPorVendedorNombre(String nombre);
    List<ViajeModel> buscarTodasPorVendedorNombreConEliminadas(String nombre);
    List<ViajeModel> buscarTodasPorOrigenDireccion(String direccion);
    List<ViajeModel> buscarTodasPorOrigenDireccionConEliminadas(String direccion);
    List<ViajeModel> buscarTodasPorDestinoDireccion(String direccion);
    List<ViajeModel> buscarTodasPorDestinoDireccionConEliminadas(String direccion);
}

