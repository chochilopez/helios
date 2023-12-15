package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.AbsCuentaCorrienteCreation;
import gloit.hiperionida.helios.mapper.dto.AbsCuentaCorrienteDTO;
import gloit.hiperionida.helios.model.CuentaCorrienteClienteModel;
import gloit.hiperionida.helios.model.FacturaModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface CuentaCorrienteClienteService extends GenericService<CuentaCorrienteClienteModel, AbsCuentaCorrienteCreation> {
    void crearMovimientosCuentaParaFactura(FacturaModel facturaModel, Long clienteId);
    List<AbsCuentaCorrienteDTO> calcularSaldo(List<AbsCuentaCorrienteDTO> listado);
    List<CuentaCorrienteClienteModel> buscarTodasPorClienteId(Long id);
    List<CuentaCorrienteClienteModel> buscarTodasPorClienteIdConEliminadas(Long id);
    List<CuentaCorrienteClienteModel> buscarTodasPorFacturaId(Long id);
    List<CuentaCorrienteClienteModel> buscarTodasPorFacturaIdConEliminadas(Long id);
}

