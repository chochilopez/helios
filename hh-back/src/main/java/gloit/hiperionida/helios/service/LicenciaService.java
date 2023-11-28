package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.LicenciaCreation;
import gloit.hiperionida.helios.model.LicenciaModel;
import gloit.hiperionida.helios.model.SeguroModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface LicenciaService extends GenericService<LicenciaModel, LicenciaCreation> {
    List<LicenciaModel> buscarTodasPorConductorId(Long id);
    List<LicenciaModel> buscarTodasPorConductorIdConEliminadas(Long id);
    List<LicenciaModel> buscarTodasPorVencimientoId(Long id);
    List<LicenciaModel> buscarTodasPorVencimientoIdConEliminadas(Long id);
    List<LicenciaModel> buscarTodasPorNotas(String notas);
    List<LicenciaModel> buscarTodasPorNotasConEliminadas(String notas);
    List<LicenciaModel> buscarTodasPorVencimientoBetween(String inicio, String fin);
    List<LicenciaModel> buscarTodasPorVencimientoBetweenConEliminadas(String inicio, String fin);
}

