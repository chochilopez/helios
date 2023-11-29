package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.CombustibleCreation;
import gloit.hiperionida.helios.model.CombustibleModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.time.LocalDateTime;
import java.util.List;

public interface CombustibleService extends GenericService<CombustibleModel, CombustibleCreation> {
    List<CombustibleModel> buscarTodasPorCamionId(Long id);
    List<CombustibleModel> buscarTodasPorCamionIdConEliminadas(Long id);
    List<CombustibleModel> buscarTodasPorConductorId(Long id);
    List<CombustibleModel> buscarTodasPorConductorIdConEliminadas(Long id);
    List<CombustibleModel> buscarTodasPorFecha(String inicio, String fin);
    List<CombustibleModel> buscarTodasPorFechaConEliminadas(String inicio, String fin);
    List<CombustibleModel> buscarTodasPorNotasContainingIgnoreCase(String notas);
    List<CombustibleModel> buscarTodasPorNotasContainingIgnoreCaseConEliminadas(String notas);
    List<CombustibleModel> buscarTodasPorProveedorId(Long id);
    List<CombustibleModel> buscarTodasPorProveedorIdConEliminadas(Long id);
}
