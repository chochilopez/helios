package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.CamionCreation;
import gloit.hiperionida.helios.model.AcopladoModel;
import gloit.hiperionida.helios.model.CamionModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;
import java.util.Optional;

public interface CamionService extends GenericService<CamionModel, CamionCreation> {
    List<CamionModel> buscarTodasPorAnio(Integer anio);
    List<CamionModel> buscarTodasPorAnioConEliminadas(Integer anio);
    List<CamionModel> buscarTodasPorMarcaModelo(String marcaModelo);
    List<CamionModel> buscarTodasPorMarcaModeloConEliminadas(String marcaModelo);
    List<CamionModel> buscarTodasPorNotas(String notas);
    List<CamionModel> buscarTodasPorNotasConEliminadas(String notas);;
    List<CamionModel> buscarTodasPorPatente(String patente);
    List<CamionModel> buscarTodasPorPatenteConEliminadas(String patente);

    List<CamionModel> buscarTodasPorNumeroChasis(String numeroChasis);
    List<CamionModel> buscarTodasPorNumeroChasisConEliminadas(String numeroChasis);
    List<CamionModel> buscarTodasPorNumeroMotor(String numeroMotor);
    List<CamionModel> buscarTodasPorNumeroMotorConEliminadas(String numeroMotor);
}
