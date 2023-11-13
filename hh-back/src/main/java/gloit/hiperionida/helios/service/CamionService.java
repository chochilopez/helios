package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.CamionCreation;
import gloit.hiperionida.helios.model.AcopladoModel;
import gloit.hiperionida.helios.model.CamionModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;
import java.util.Optional;

public interface CamionService extends GenericService<CamionModel, CamionCreation> {
    CamionModel buscarPorSeguroId(Long id);
    CamionModel buscarPorSeguroIdConEliminadas(Long id);
    List<CamionModel> buscarTodasPorMarcaModelo(String marcaModelo);
    List<CamionModel> buscarTodasPorMarcaModeloConEliminadas(String marcaModelo);
    List<CamionModel> buscarTodasPorRangoAnio(Integer min, Integer max);
    List<CamionModel> buscarTodasPorRangoAnioConEliminadas(Integer min, Integer max);
    List<CamionModel> buscarTodasPorRangoCantidadNeumaticos(Integer min, Integer max);
    List<CamionModel> buscarTodasPorRangoCantidadNeumaticosConEliminadas(Integer min, Integer max);
    List<CamionModel> buscarTodasPorNotas(String notas);
    List<CamionModel> buscarTodasPorNotasConEliminadas(String notas);

    List<CamionModel> buscarTodasPorNumeroChasis(String numeroChasis);
    List<CamionModel> buscarTodasPorNumeroChasisConEliminadas(String numeroChasis);
    List<CamionModel> buscarTodasPorNumeroMotor(String numeroMotor);
    List<CamionModel> buscarTodasPorNumeroMotorConEliminadas(String numeroMotor);
}
