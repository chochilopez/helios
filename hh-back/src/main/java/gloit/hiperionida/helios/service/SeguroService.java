package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.SeguroCreation;
import gloit.hiperionida.helios.model.SeguroModel;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;
import java.util.Optional;

public interface SeguroService extends GenericService<SeguroModel, SeguroCreation> {
    List<SeguroModel> buscarTodasPorAcopladoId(Long id);
    List<SeguroModel> buscarTodasPorAcopladoIdConEliminadas(Long id);
    List<SeguroModel> buscarTodasPorAseguradoraId(Long id);
    List<SeguroModel> buscarTodasPorAseguradoraIdConEliminadas(Long id);
    List<SeguroModel> buscarTodasPorCamionId(Long id);
    List<SeguroModel> buscarTodasPorCamionIdConEliminadas(Long id);
    List<SeguroModel> buscarTodasPorVencimientoId(Long id);
    List<SeguroModel> buscarTodasPorVencimientoIdConEliminadas(Long id);
    List<SeguroModel> buscarTodasPorNotas(String notas);
    List<SeguroModel> buscarTodasPorNotasConEliminadas(String notas);
    List<SeguroModel> buscarTodasPorVencimientoBetween(String inicio, String fin);
    List<SeguroModel> buscarTodasPorVencimientoBetweenConEliminadas(String inicio, String fin);
}

