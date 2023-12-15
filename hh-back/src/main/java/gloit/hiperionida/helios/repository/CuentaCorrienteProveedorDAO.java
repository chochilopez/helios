package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.CuentaCorrienteProveedorModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaCorrienteProveedorDAO extends GenericDTO<CuentaCorrienteProveedorModel> {
    List<CuentaCorrienteProveedorModel> findAllByProveedorId(Long id);
    List<CuentaCorrienteProveedorModel> findAllByProveedorIdAndEliminadaIsNull(Long id);
}
