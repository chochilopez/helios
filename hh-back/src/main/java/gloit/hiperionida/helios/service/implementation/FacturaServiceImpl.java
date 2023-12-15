package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.FacturaMapper;
import gloit.hiperionida.helios.mapper.creation.FacturaCreation;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.repository.FacturaDAO;
import gloit.hiperionida.helios.service.FacturaService;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.exception.ObjectoNoEliminadoException;
import gloit.hiperionida.helios.util.exception.ParametroInvalidoException;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {
    private final FacturaDAO facturaDAO;
    private final FacturaMapper facturaMapper;
    private final CuentaCorrienteClienteServiceImpl cuentaCorrienteService;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public FacturaModel marcarComoPagada(Long id) {
        log.info("Marcando la factura con id como pagada: {}.", id);
        FacturaModel facturaModel = this.buscarPorId(id);
        facturaModel.setPagada(true);
        log.info("Se marcó una entidad Factura como pagada, id: " + id + ".");
        return this.crear(facturaModel);
    }

    @Override
    public FacturaModel buscarPorViajeId(Long id) {
        log.info("Buscando la entidad Factura con id de viaje: {}.", id);
        FacturaModel facturaModel = facturaDAO.findByViajeIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Factura con id de viaje: " + id + "."));
        log.info("Se encontró una entidad Factura con id de viaje: " + id + ".");
        return facturaModel;
    }

    @Override
    public FacturaModel buscarPorViajeIdConEliminadas(Long id) {
        log.info("Buscando la entidad Factura con id de viaje: {}, incluidas las eliminadas.", id);
        FacturaModel facturaModel = facturaDAO.findByViajeId(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Factura con id de viaje: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Factura con id de viaje: " + id + ", incluidas las eliminadas.");
        return facturaModel;
    }

    @Override
    public FacturaModel buscarPorRemitoId(Long id) {
        log.info("Buscando la entidad Factura con id de remito: {}.", id);
        FacturaModel facturaModel = facturaDAO.findByRemitoIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Factura con id de remito: " + id + "."));
        log.info("Se encontró una entidad Factura con id de remito: " + id + ".");
        return facturaModel;
    }

    @Override
    public FacturaModel buscarPorRemitoIdConEliminadas(Long id) {
        log.info("Buscando la entidad Factura con id de remito: {}, incluidas las eliminadas.", id);
        FacturaModel facturaModel = facturaDAO.findByRemitoId(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Factura con id de remito: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Factura con id de remito: " + id + ", incluidas las eliminadas.");
        return facturaModel;
    }

    @Override
    public List<FacturaModel> buscarTodasPorClienteIdNoPagadas(Long id) {
        log.info("Buscando todas las entidades Factura con id de cliente: {} y no pagadas.", id);
        List<FacturaModel> listado = facturaDAO.findAllByClienteIdAndPagadaIsFalseAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Factura con id de cliente con id de cliente: " + id + " y no pagadas.");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorClienteIdNoPagadasConEliminadas(Long id) {
        log.info("Buscando todas las entidades Factura con id de cliente: {} y no pagadas, incluidas las eliminadas.", id);
        List<FacturaModel> listado = facturaDAO.findAllByClienteIdAndPagadaIsFalse(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Factura con id de cliente con id de cliente: " + id + " y no pagadas, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorCodigo(String codigo) {
        log.info("Buscando todas las entidades Factura con codigo: {}.", codigo);
        List<FacturaModel> listado = facturaDAO.findAllByCodigoContainingIgnoreCaseAndEliminadaIsNull(codigo);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades con codigo: " + codigo + ".");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorCodigoConEliminadas(String codigo) {
        log.info("Buscando todas las entidades Factura con codigo: {}, incluidas las eliminadas.", codigo);
        List<FacturaModel> listado = facturaDAO.findAllByCodigoContainingIgnoreCase(codigo);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades con codigo: " + codigo + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorConcepto(String concepto) {
        log.info("Buscando todas las entidades Factura con concepto: {}.", concepto);
        List<FacturaModel> listado = facturaDAO.findAllByConceptoContainingIgnoreCaseAndEliminadaIsNull(concepto);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades con concepto: " + concepto + ".");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorConceptoConEliminadas(String concepto) {
        log.info("Buscando todas las entidades Factura con concepto: {}, incluidas las eliminadas.", concepto);
        List<FacturaModel> listado = facturaDAO.findAllByConceptoContainingIgnoreCase(concepto);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades con concepto: " + concepto + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorFechaEmisionBetween(String inicio, String fin) {
        log.info("Buscando todas las entidades entre las fechas de emisión: {} y {}.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son válidas.");
        List<FacturaModel> listado = facturaDAO.findAllByFechaEmisionBetweenAndEliminadaIsNull( fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Factura entre las fechas de emisión: " + inicio + " y " + fin + ".");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorFechaEmisionBetweenConEliminadas(String inicio, String fin) {
        log.info("Buscando todas las entidades entre las fechas de emisión: {} y {}, incluidas las eliminadas.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son válidas.");
        List<FacturaModel> listado = facturaDAO.findAllByFechaEmisionBetween( fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Factura entre las fechas de emisión: " + inicio + " y " + fin + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorFechaVencimientoBetween(String inicio, String fin) {
        log.info("Buscando todas las entidades entre las fechas de vencimiento: {} y {}.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son válidas.");
        List<FacturaModel> listado = facturaDAO.findAllByFechaVencimientoBetweenAndEliminadaIsNull( fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Factura entre las fechas de vencimiento: " + inicio + " y " + fin + ".");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorFechaVencimientoBetweenConEliminadas(String inicio, String fin) {
        log.info("Buscando todas las entidades entre las fechas de vencimiento: {} y {}, incluidas las eliminadas.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son válidas.");
        List<FacturaModel> listado = facturaDAO.findAllByFechaVencimientoBetween( fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Factura entre las fechas de vencimiento: " + inicio + " y " + fin + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorNotas(String notas) {
        log.info("Buscando todas las entidades Factura con notas: {}.", notas);
        List<FacturaModel> listado = facturaDAO.findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades con notas: " + notas + ".");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorNotasConEliminadas(String notas) {
        log.info("Buscando todas las entidades Factura con notas: {}, incluidas las eliminadas.", notas);
        List<FacturaModel> listado = facturaDAO.findAllByNotasContainingIgnoreCase(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades con notas: " + notas + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorNumeroComprobante(String numeroComprobante) {
        log.info("Buscando todas las entidades Factura con numeroComprobante: {}.", numeroComprobante);
        List<FacturaModel> listado = facturaDAO.findAllByNumeroComprobanteContainingIgnoreCaseAndEliminadaIsNull(numeroComprobante);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades con numeroComprobante: " + numeroComprobante + ".");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorNumeroComprobanteConEliminadas(String numeroComprobante) {
        log.info("Buscando todas las entidades Factura con numeroComprobante: {}, incluidas las eliminadas.", numeroComprobante);
        List<FacturaModel> listado = facturaDAO.findAllByNumeroComprobanteContainingIgnoreCase(numeroComprobante);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades con numeroComprobante: " + numeroComprobante + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorPagada(Boolean pagada) {
        log.info("Buscando todas las entidades Factura con pagada: {}.", pagada);
        List<FacturaModel> listado = facturaDAO.findAllByPagadaAndEliminadaIsNull(pagada);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades con pagada: " + pagada + ".");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorPagadaConEliminadas(Boolean pagada) {
        log.info("Buscando todas las entidades Factura con pagada: {}, incluidas las eliminadas.", pagada);
        List<FacturaModel> listado = facturaDAO.findAllByPagada(pagada);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades con pagada: " + pagada + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorRazonSocial(String razonSocial) {
        log.info("Buscando todas las entidades Factura con razonSocial: {}.", razonSocial);
        List<FacturaModel> listado = facturaDAO.findAllByRazonSocialContainingIgnoreCaseAndEliminadaIsNull(razonSocial);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades con razonSocial: " + razonSocial + ".");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorRazonSocialConEliminadas(String razonSocial) {
        log.info("Buscando todas las entidades Factura con razonSocial: {}, incluidas las eliminadas.", razonSocial);
        List<FacturaModel> listado = facturaDAO.findAllByRazonSocialContainingIgnoreCase(razonSocial);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades con razonSocial: " + razonSocial + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorTipoComprobante(String tipoComprobante) {
        log.info("Buscando todas las entidades Factura con tipoComprobante: {}.", tipoComprobante);
        List<FacturaModel> listado = facturaDAO.findAllByTipoComprobanteAndEliminadaIsNull(TipoComprobanteEnum.valueOf(tipoComprobante));
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades con tipoComprobante: " + tipoComprobante + ".");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasPorTipoComprobanteConEliminadas(String tipoComprobante) {
        log.info("Buscando todas las entidades Factura con tipoComprobante: {}, incluidas las eliminadas.", tipoComprobante);
        List<FacturaModel> listado = facturaDAO.findAllByTipoComprobante(TipoComprobanteEnum.valueOf(tipoComprobante));
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades con tipoComprobante: " + tipoComprobante + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public FacturaModel buscarPorId(Long id) {
        log.info("Buscando la entidad Factura con id: {}.", id);
        FacturaModel facturaModel = facturaDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Factura con id: " + id + "."));
        log.info("Se encontró una entidad Factura con id: " + id + ".");
        return facturaModel;
    }

    @Override
    public FacturaModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Factura con id: {}, incluidas las eliminadas.", id);
        FacturaModel facturaModel = facturaDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Factura con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Factura con id: " + id + ", incluidas las eliminadas.");
        return facturaModel;
    }

    @Override
    public List<FacturaModel> buscarTodas() {
        log.info("Buscando todas las entidades Factura.");
        List<FacturaModel> listado = facturaDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Factura.");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Factura, incluidas las eliminadas.");
        List<FacturaModel> listado = facturaDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Factura, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<FacturaModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Factura, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<FacturaModel> slice = facturaDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Factura.");
        return slice;
    }

    @Override
    public Slice<FacturaModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Factura, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<FacturaModel> slice = facturaDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Factura, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = facturaDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Factura.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = facturaDAO.count();
        log.info("Existen {} entidades Factura, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public FacturaModel crear(FacturaModel model) {
        log.info("Insertando la entidad FacturaModel: {}.",  model);
        FacturaModel facturaModel = facturaDAO.save(model);
        if (model.getId() == null) {
            facturaModel.setCreada(Helper.getNow(""));
            facturaModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad FacturaModel.");
        } else {
            facturaModel.setModificada(Helper.getNow(""));
            facturaModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad FacturaModel.");
        }

        return facturaDAO.save(facturaModel);
    }

    @Override
    public FacturaModel guardar(FacturaCreation creation) {
        log.info("Insertando la entidad FacturaCreation: {}.",  creation);
        FacturaModel facturaModel = facturaDAO.save(facturaMapper.toEntity(creation));
        if (creation.getId() == null) {
            facturaModel.setCreada(Helper.getNow(""));
            facturaModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad FacturaCreation.");
        } else {
            facturaModel.setModificada(Helper.getNow(""));
            facturaModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad FacturaCreation.");
        }

        if (Helper.getLong(creation.getClienteId()) != null) {
            cuentaCorrienteService.crearMovimientosCuentaParaFactura(facturaModel, Helper.getLong(creation.getClienteId()));
        }
        return facturaDAO.save(facturaModel);
    }

    @Override
    public FacturaModel eliminar(Long id) {
        log.info("Eliminando la entidad Factura con id: {}.", id);
        FacturaModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Factura con id: " + id + ", fue eliminada correctamente.");
        return facturaDAO.save(objeto);
    }

    @Override
    public FacturaModel reciclar(Long id) {
        log.info("Reciclando la entidad Factura con id: {}.", id);
        FacturaModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Factura con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Factura con id: " + id + ", fue reciclada correctamente.");
        return facturaDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Factura con id: {}.", id);
        FacturaModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Factura con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        facturaDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
