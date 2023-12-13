package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.CombustibleCreation;
import gloit.hiperionida.helios.model.CombustibleModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface CombustibleService extends GenericService<CombustibleModel, CombustibleCreation> {
    List<CombustibleModel> buscarTodasPorCajaId(Long id);
    List<CombustibleModel> buscarTodasPorCajaIdConEliminadas(Long id);
    List<CombustibleModel> buscarTodasPorCamionId(Long id);
    List<CombustibleModel> buscarTodasPorCamionIdConEliminadas(Long id);
    List<CombustibleModel> buscarTodasPorConductorId(Long id);
    List<CombustibleModel> buscarTodasPorConductorIdConEliminadas(Long id);
    List<CombustibleModel> buscarTodasPorFechaEntre(String inicio, String fin);
    List<CombustibleModel> buscarTodasPorFechaEntreConEliminadas(String inicio, String fin);
    List<CombustibleModel> buscarTodasPorKilometrosEntre(Double min, Double max);
    List<CombustibleModel> buscarTodasPorKilometrosEntreConEliminadas(Double min, Double max);
    List<CombustibleModel> buscarTodasPorLitrosEntre(Double min, Double max);
    List<CombustibleModel> buscarTodasPorLitrosEntreConEliminadas(Double min, Double max);
    List<CombustibleModel> buscarTodasPorNotasContainingIgnoreCase(String notas);
    List<CombustibleModel> buscarTodasPorNotasContainingIgnoreCaseConEliminadas(String notas);
    List<CombustibleModel> buscarTodasPorPrecioEntre(Double min, Double max);
    List<CombustibleModel> buscarTodasPorPrecioEntreConEliminadas(Double min, Double max);
    List<CombustibleModel> buscarTodasPorProveedorId(Long id);
    List<CombustibleModel> buscarTodasPorProveedorIdConEliminadas(Long id);
}
