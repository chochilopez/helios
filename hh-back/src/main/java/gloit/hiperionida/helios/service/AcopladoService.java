package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.AcopladoCreation;
import gloit.hiperionida.helios.model.AcopladoModel;
import gloit.hiperionida.helios.model.CamionModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;
import java.util.Optional;

public interface AcopladoService extends GenericService<AcopladoModel, AcopladoCreation> {
    AcopladoModel buscarPorSeguroId(Long id);
    AcopladoModel buscarPorSeguroIdConEliminadas(Long id);
    List<AcopladoModel> buscarTodasPorMarcaModelo(String marcaModelo);
    List<AcopladoModel> buscarTodasPorMarcaModeloConEliminadas(String marcaModelo);
    List<AcopladoModel> buscarTodasPorRangoAnio(Integer min, Integer max);
    List<AcopladoModel> buscarTodasPorRangoAnioConEliminadas(Integer min, Integer max);
    List<AcopladoModel> buscarTodasPorRangoCantidadNeumaticos(Integer min, Integer max);
    List<AcopladoModel> buscarTodasPorRangoCantidadNeumaticosConEliminadas(Integer min, Integer max);
    List<AcopladoModel> buscarTodasPorNotas(String notas);
    List<AcopladoModel> buscarTodasPorNotasConEliminadas(String notas);
}

