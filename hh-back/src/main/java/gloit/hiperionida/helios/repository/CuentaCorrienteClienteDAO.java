package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.CuentaCorrienteClienteModel;
import gloit.hiperionida.helios.util.repository.GenericoDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaCorrienteClienteDAO extends GenericoDAO<CuentaCorrienteClienteModel> {
    List<CuentaCorrienteClienteModel> findAllByClienteId(Long id);
    List<CuentaCorrienteClienteModel> findAllByClienteIdAndEliminadaIsNull(Long id);
    List<CuentaCorrienteClienteModel> findAllByFacturaId(Long id);
    List<CuentaCorrienteClienteModel> findAllByFacturaIdAndEliminadaIsNull(Long id);
}
