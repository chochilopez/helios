package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.PresupuestoCreation;
import gloit.hiperionida.helios.model.PresupuestoModel;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface PresupuestoService extends GenericService<PresupuestoModel, PresupuestoCreation> {
    List<PresupuestoModel> buscarTodasPorCategoriaViajeId(Long id);
    List<PresupuestoModel> buscarTodasPorCategoriaViajeIdConEliminadas(Long id);
    List<PresupuestoModel> buscarTodasPorClienteId(Long id);
    List<PresupuestoModel> buscarTodasPorClienteIdConEliminadas(Long id);
    List<PresupuestoModel> buscarTodasPorDestinoId(Long id);
    List<PresupuestoModel> buscarTodasPorDestinoIdConEliminadas(Long id);
    List<PresupuestoModel> buscarTodasPorFechaEntreFechas(String inicio, String fin);
    List<PresupuestoModel> buscarTodasPorFechaEntreFechasConEliminadas(String inicio, String fin);
    List<PresupuestoModel> buscarTodasPorNotas(String nota);
    List<PresupuestoModel> buscarTodasPorNotasConEliminadas(String nota);
    List<PresupuestoModel> buscarTodasPorOrigenId(Long id);
    List<PresupuestoModel> buscarTodasPorOrigenIdConEliminadas(Long id);
    List<PresupuestoModel> buscarTodasPorRangoCantidadTransportada(Integer min, Integer max);
    List<PresupuestoModel> buscarTodasPorRangoCantidadTransportadaConEliminadas(Integer min, Integer max);
    List<PresupuestoModel> buscarTodasPorRangoKmCargado(Double min, Double max);
    List<PresupuestoModel> buscarTodasPorRangoKmCargadoConEliminadas(Double min, Double max);
    List<PresupuestoModel> buscarTodasPorRangoValorKm(Double min, Double max);
    List<PresupuestoModel> buscarTodasPorRangoValorKmConEliminadas(Double min, Double max);

    List<PresupuestoModel> buscarTodasPorClienteNombre(String nombre);
    List<PresupuestoModel> buscarTodasPorClienteNombreConEliminadas(String nombre);
    List<PresupuestoModel> buscarTodasPorOrigenDireccion(String direccion);
    List<PresupuestoModel> buscarTodasPorOrigenDireccionConEliminadas(String direccion);
    List<PresupuestoModel> buscarTodasPorDestinoDireccion(String direccion);
    List<PresupuestoModel> buscarTodasPorDestinoDireccionConEliminadas(String direccion);

    PresupuestoModel buscarProximo();
    PresupuestoModel buscarUltimo();
}

