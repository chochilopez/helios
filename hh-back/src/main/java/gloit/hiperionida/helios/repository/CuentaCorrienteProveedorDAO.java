package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.CuentaCorrienteProveedorModel;
import gloit.hiperionida.helios.util.repository.GenericoDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaCorrienteProveedorDAO extends GenericoDAO<CuentaCorrienteProveedorModel> {
    List<CuentaCorrienteProveedorModel> findAllByProveedorId(Long id);
    List<CuentaCorrienteProveedorModel> findAllByProveedorIdAndEliminadaIsNull(Long id);
}
