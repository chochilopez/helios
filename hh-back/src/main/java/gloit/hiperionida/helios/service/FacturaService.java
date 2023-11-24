package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.FacturaCreation;
import gloit.hiperionida.helios.model.FacturaModel;
import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.util.service.GenericService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FacturaService extends GenericService<FacturaModel, FacturaCreation> {
    FacturaModel marcarComoPagada(Long id);
    FacturaModel buscarPorViajeId(Long id);
    FacturaModel buscarPorViajeIdConEliminadas(Long id);
    FacturaModel buscarPorRemitoId(Long id);
    FacturaModel buscarPorRemitoIdConEliminadas(Long id);
    List<FacturaModel> buscarTodasPorClienteIdNoPagadas(Long id);
    List<FacturaModel> buscarTodasPorClienteIdNoPagadasConEliminadas(Long id);
    List<FacturaModel> buscarTodasPorCodigo(String codigo);
    List<FacturaModel> buscarTodasPorCodigoConEliminadas(String codigo);
    List<FacturaModel> buscarTodasPorConcepto(String concepto);
    List<FacturaModel> buscarTodasPorConceptoConEliminadas(String concepto);
    List<FacturaModel> buscarTodasPorFechaEmisionBetween(String inicio, String fin);
    List<FacturaModel> buscarTodasPorFechaEmisionBetweenConEliminadas(String inicio, String fin);
    List<FacturaModel> buscarTodasPorFechaVencimientoBetween(String inicio, String fin);
    List<FacturaModel> buscarTodasPorFechaVencimientoBetweenConEliminadas(String inicio, String fin);
    List<FacturaModel> buscarTodasPorNotas(String notas);
    List<FacturaModel> buscarTodasPorNotasConEliminadas(String notas);
    List<FacturaModel> buscarTodasPorNumeroComprobante(String numeroComprobante);
    List<FacturaModel> buscarTodasPorNumeroComprobanteConEliminadas(String numeroComprobante);
    List<FacturaModel> buscarTodasPorPagada(Boolean pagada);
    List<FacturaModel> buscarTodasPorPagadaConEliminadas(Boolean pagada);
    List<FacturaModel> buscarTodasPorRazonSocial(String razonSocial);
    List<FacturaModel> buscarTodasPorRazonSocialConEliminadas(String razonSocial);
    List<FacturaModel> buscarTodasPorTipoComprobante(String tipoComprobante);
    List<FacturaModel> buscarTodasPorTipoComprobanteConEliminadas(String tipoComprobante);
}

