package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.CuentaCorrienteModel;
import gloit.hiperionida.helios.model.PagoModel;
import gloit.hiperionida.helios.model.enums.MovimientoEnum;
import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CuentaCorrienteDAO extends GenericDTO<CuentaCorrienteModel> {
    List<CuentaCorrienteModel> findAllByClienteId(Long id);
    List<CuentaCorrienteModel> findAllByClienteIdAndEliminadaIsNull(Long id);
    List<CuentaCorrienteModel> findAllByFacturaId(Long id);
    List<CuentaCorrienteModel> findAllByFacturaIdAndEliminadaIsNull(Long id);
}
