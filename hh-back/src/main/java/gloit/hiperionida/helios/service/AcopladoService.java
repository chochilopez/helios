package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.AcopladoCreation;
import gloit.hiperionida.helios.model.AcopladoModel;
import gloit.hiperionida.helios.model.CamionModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;
import java.util.Optional;

public interface AcopladoService extends GenericService<AcopladoModel, AcopladoCreation> {
    List<AcopladoModel> buscarTodasPorAnio(Integer anio);
    List<AcopladoModel> buscarTodasPorAnioConEliminadas(Integer anio);
    List<AcopladoModel> buscarTodasPorMarcaModelo(String marcaModelo);
    List<AcopladoModel> buscarTodasPorMarcaModeloConEliminadas(String marcaModelo);
    List<AcopladoModel> buscarTodasPorNotas(String notas);
    List<AcopladoModel> buscarTodasPorNotasConEliminadas(String notas);
    List<AcopladoModel> buscarTodasPorPatente(String patente);
    List<AcopladoModel> buscarTodasPorPatenteConEliminadas(String patente);
}

