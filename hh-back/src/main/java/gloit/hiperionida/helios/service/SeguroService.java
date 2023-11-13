package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.SeguroCreation;
import gloit.hiperionida.helios.model.SeguroModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;
import java.util.Optional;

public interface SeguroService extends GenericService<SeguroModel, SeguroCreation> {
    SeguroModel buscarPorAseguradoraId(Long id);
    SeguroModel buscarPorAseguradoraIdConEliminadas(Long id);
    SeguroModel buscarPorVencimientoId(Long id);
    SeguroModel buscarPorVencimientoIdConEliminadas(Long id);
    List<SeguroModel> buscarTodasPorNotas(String notas);
    List<SeguroModel> buscarTodasPorNotasConEliminadas(String notas);
}

