package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.CuentaCorrienteProveedorCreation;
import gloit.hiperionida.helios.mapper.dto.AbsCuentaCorrienteDTO;
import gloit.hiperionida.helios.mapper.dto.CuentaCorrienteProveedorDTO;
import gloit.hiperionida.helios.model.CuentaCorrienteProveedorModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface CuentaCorrienteProveedorService extends GenericService<CuentaCorrienteProveedorModel, CuentaCorrienteProveedorCreation> {
    List<CuentaCorrienteProveedorDTO> calcularSaldo(List<AbsCuentaCorrienteDTO> listado);
    List<CuentaCorrienteProveedorModel> buscarTodasPorProveedorId(Long id);
    List<CuentaCorrienteProveedorModel> buscarTodasPorProveedorIdConEliminadas(Long id);
}

