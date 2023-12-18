package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.CuentaCorrienteClienteCreation;
import gloit.hiperionida.helios.mapper.dto.CuentaCorrienteClienteDTO;
import gloit.hiperionida.helios.model.CuentaCorrienteClienteModel;
import gloit.hiperionida.helios.model.FacturaModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface CuentaCorrienteClienteService extends GenericService<CuentaCorrienteClienteModel, CuentaCorrienteClienteCreation> {
    void crearMovimientosCuentaParaFactura(FacturaModel facturaModel, Long clienteId);
    List<CuentaCorrienteClienteDTO> calcularSaldo(List<CuentaCorrienteClienteDTO> listado);
    List<CuentaCorrienteClienteModel> buscarTodasPorClienteId(Long id);
    List<CuentaCorrienteClienteModel> buscarTodasPorClienteIdConEliminadas(Long id);
    List<CuentaCorrienteClienteModel> buscarTodasPorFacturaId(Long id);
    List<CuentaCorrienteClienteModel> buscarTodasPorFacturaIdConEliminadas(Long id);
}

