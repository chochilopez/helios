package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.CuentaCorrienteCreation;
import gloit.hiperionida.helios.model.CuentaCorrienteModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface CuentaCorrienteService extends GenericService<CuentaCorrienteModel, CuentaCorrienteCreation> {
    List<CuentaCorrienteModel> buscarTodasPorClienteId(Long id);
    List<CuentaCorrienteModel> buscarTodasPorClienteIdConEliminadas(Long id);
}

