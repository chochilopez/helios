package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.AdelantoCreation;
import gloit.hiperionida.helios.model.AdelantoModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface AdelantoService extends GenericService<AdelantoModel, AdelantoCreation> {
    List<AdelantoModel> buscarTodasPorCajaId(Long id);
    List<AdelantoModel> buscarTodasPorCajaIdConEliminadas(Long id);
    List<AdelantoModel> buscarTodasPorConductorId(Long id);
    List<AdelantoModel> buscarTodasPorConductorIdConEliminadas(Long id);
    List<AdelantoModel> buscarTodasPorFechaEntre(String inicio, String fin);
    List<AdelantoModel> buscarTodasPorFechaEntreConEliminadas(String inicio, String fin);
    List<AdelantoModel> buscarTodasPorMontoEntre(Double min, Double max);
    List<AdelantoModel> buscarTodasPorMontoEntreConEliminadas(Double min, Double max);
    List<AdelantoModel> buscarTodasPorNotas(String notas);
    List<AdelantoModel> buscarTodasPorNotasConEliminadas(String notas);
}

