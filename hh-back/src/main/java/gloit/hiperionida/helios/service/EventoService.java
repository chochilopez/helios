package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.EventoCreation;
import gloit.hiperionida.helios.model.EventoModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface EventoService extends GenericService<EventoModel, EventoCreation> {
    List<EventoModel> buscarTodasPorRecordatorioActivo();
    List<EventoModel> buscarTodasPorRecordatorioActivoConEliminadas();
}