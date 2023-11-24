package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.CuentaCorrienteCreation;
import gloit.hiperionida.helios.mapper.dto.CuentaCorrienteDTO;
import gloit.hiperionida.helios.model.CuentaCorrienteModel;
import gloit.hiperionida.helios.model.FacturaModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface CuentaCorrienteService extends GenericService<CuentaCorrienteModel, CuentaCorrienteCreation> {
    void crearMovimientosCuentaParaFactura(FacturaModel facturaModel, Long clienteId);
    List<CuentaCorrienteDTO> calcularSaldo(List<CuentaCorrienteDTO> listado);
    List<CuentaCorrienteModel> buscarTodasPorClienteId(Long id);
    List<CuentaCorrienteModel> buscarTodasPorClienteIdConEliminadas(Long id);
    List<CuentaCorrienteModel> buscarTodasPorFacturaId(Long id);
    List<CuentaCorrienteModel> buscarTodasPorFacturaIdConEliminadas(Long id);
}

