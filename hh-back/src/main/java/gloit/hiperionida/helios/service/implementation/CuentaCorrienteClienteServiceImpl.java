package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.CuentaCorrienteClienteMapper;
import gloit.hiperionida.helios.mapper.creation.AbsCuentaCorrienteCreation;
import gloit.hiperionida.helios.mapper.creation.ReciboCreation;
import gloit.hiperionida.helios.mapper.dto.AbsCuentaCorrienteDTO;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.model.enums.MovimientoEnum;
import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
import gloit.hiperionida.helios.repository.CuentaCorrienteClienteDAO;
import gloit.hiperionida.helios.service.CuentaCorrienteClienteService;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.exception.ObjectoNoEliminadoException;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class CuentaCorrienteClienteServiceImpl implements CuentaCorrienteClienteService {
    private final CuentaCorrienteClienteDAO cuentaCorrienteClienteDAO;
    private final CuentaCorrienteClienteMapper cuentaCorrienteClienteMapper;
    private final ReciboServiceImpl reciboService;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public void crearMovimientosCuentaParaFactura(FacturaModel facturaModel, Long clienteId) {
        Double total = facturaModel.getSubTotal();
        if (facturaModel.getBonificacion() != null) {
            Double bonificacion = Helper.getNDecimal((facturaModel.getBonificacion() * total) / 100, 2);
            total = Helper.getNDecimal(total - bonificacion, 2);
        }
        if (facturaModel.getOtrosImpuestos() != null) {
            Double otrosImpuestos = Helper.getNDecimal((facturaModel.getOtrosImpuestos() * total) / 100, 2);
            total = Helper.getNDecimal(total + otrosImpuestos, 2);
        }
        if (facturaModel.getIva() != null) {
            Double iva = Helper.getNDecimal((facturaModel.getIva() * total) / 100, 2);
            total = Helper.getNDecimal(total + iva, 2);
        }
        this.guardar(new AbsCuentaCorrienteCreation(
                null,
                total.toString(),
                "Comprobante " + facturaModel.getTipoComprobante().toString() + "-" + facturaModel.getNumeroComprobante(),
                null,
                MovimientoEnum.DEBITO.toString(),
                clienteId.toString(),
                null,
                facturaModel.getId().toString()
        ));
        if (facturaModel.getPagada()) {
            CuentaCorrienteClienteModel ctaCte = this.guardar(new AbsCuentaCorrienteCreation(
                    null,
                    total.toString(),
                    "Comprobante " + facturaModel.getTipoComprobante().toString() + "-" + facturaModel.getNumeroComprobante(),
                    TipoPagoEnum.EFECTIVO.toString(),
                    MovimientoEnum.CREDITO.toString(),
                    clienteId.toString(),
                    null,
                    facturaModel.getId().toString()
            ));
            ReciboModel recibo = reciboService.guardar(new ReciboCreation(null, total.toString(), Helper.getNow("").toString()));
            ctaCte.setReciboId(recibo.getId());
            cuentaCorrienteClienteDAO.save(ctaCte);
        }
    }

    @Override
    public List<AbsCuentaCorrienteDTO> calcularSaldo(List<AbsCuentaCorrienteDTO> listado) {
        for (int a = 0; a < listado.size(); a++ ) {
            if (a == 0) {
                if (Objects.equals(listado.get(a).getTipoMovimiento(), "DEBITO")) {
                    Double valor = Helper.getDecimal(listado.get(a).getMonto()) * -1;
                    valor = Helper.getNDecimal(valor, 2);
                    listado.get(a).setSaldo(valor.toString());
                } else if (Objects.equals(listado.get(a).getTipoMovimiento(), "CREDITO")) {
                    Double valor = Helper.getDecimal(listado.get(a).getMonto());
                    valor = Helper.getNDecimal(valor, 2);
                    listado.get(a).setSaldo(valor.toString());
                }
            } else if (a > 0) {
                if (Objects.equals(listado.get(a).getTipoMovimiento(), "DEBITO")) {
                    Double valor1 = Helper.getDecimal(listado.get(a - 1).getSaldo());
                    Double valor2 = Helper.getDecimal(listado.get(a).getMonto()) * -1;
                    Double valor3 = 0.0;
                    valor3 = valor1 + valor2;
//                    if (Objects.equals(listado.get(a).getTipoMovimiento(), "DEBITO")) {
//                        valor3 = valor1 - valor2;
//                    } else if (Objects.equals(listado.get(a).getTipoMovimiento(), "CREDITO")) {
//                        valor3 = valor1 + valor2;
//                    }
                    valor3 = Helper.getNDecimal(valor3, 2);
                    listado.get(a).setSaldo(valor3.toString());
                } else if (Objects.equals(listado.get(a).getTipoMovimiento(), "CREDITO")) {
                    Double valor1 = Helper.getDecimal(listado.get(a - 1).getSaldo());
                    Double valor2 = Helper.getDecimal(listado.get(a).getMonto());
                    Double valor3 = 0.0;
                    valor3 = valor1 + valor2;
//                    if (Objects.equals(listado.get(a).getTipoMovimiento(), "DEBITO")) {
//                        valor3 = valor1 - valor2;
//                    } else if (Objects.equals(listado.get(a).getTipoMovimiento(), "CREDITO")) {
//                        valor3 = valor1 + valor2;
//                    }
                    valor3 = Helper.getNDecimal(valor3, 2);
                    listado.get(a).setSaldo(valor3.toString());
                }
            }
        }
        return listado;
    }

    @Override
    public List<CuentaCorrienteClienteModel> buscarTodasPorClienteId(Long id) {
        log.info("Buscando todas las entidades CuentaCorriente con cliente id: {}.", id);
        List<CuentaCorrienteClienteModel> listado = cuentaCorrienteClienteDAO.findAllByClienteIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente con cliente id: " + id + ".");
        return listado;
    }

    @Override
    public List<CuentaCorrienteClienteModel> buscarTodasPorClienteIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades CuentaCorriente con cliente id: {}, incluidas las eliminadas.", id);
        List<CuentaCorrienteClienteModel> listado = cuentaCorrienteClienteDAO.findAllByClienteId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente con cliente id: " + id + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<CuentaCorrienteClienteModel> buscarTodasPorFacturaId(Long id) {
        log.info("Buscando todas las entidades CuentaCorriente con factura id: {}.", id);
        List<CuentaCorrienteClienteModel> listado = cuentaCorrienteClienteDAO.findAllByFacturaIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente con factura id: " + id + ".");
        return listado;
    }

    @Override
    public List<CuentaCorrienteClienteModel> buscarTodasPorFacturaIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades CuentaCorriente con factura id: {}, incluidas las eliminadas.", id);
        List<CuentaCorrienteClienteModel> listado = cuentaCorrienteClienteDAO.findAllByFacturaId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente con factura id: " + id + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public CuentaCorrienteClienteModel buscarPorId(Long id) {
        log.info("Buscando la entidad CuentaCorriente con id: {}.", id);
        CuentaCorrienteClienteModel cuentaCorrienteClienteModel = cuentaCorrienteClienteDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad CuentaCorriente con id: " + id + "."));
        log.info("Se encontró una entidad CuentaCorriente con id: " + id + ".");
        return cuentaCorrienteClienteModel;
    }

    @Override
    public CuentaCorrienteClienteModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad CuentaCorriente con id: {}, incluidas las eliminadas.", id);
        CuentaCorrienteClienteModel cuentaCorrienteClienteModel = cuentaCorrienteClienteDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad CuentaCorriente con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad CuentaCorriente con id: " + id + ", incluidas las eliminadas.");
        return cuentaCorrienteClienteModel;
    }

    @Override
    public List<CuentaCorrienteClienteModel> buscarTodas() {
        log.info("Buscando todas las entidades CuentaCorriente.");
        List<CuentaCorrienteClienteModel> listado = cuentaCorrienteClienteDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente.");
        return listado;
    }

    @Override
    public List<CuentaCorrienteClienteModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades CuentaCorriente, incluidas las eliminadas.");
        List<CuentaCorrienteClienteModel> listado = cuentaCorrienteClienteDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<CuentaCorrienteClienteModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades CuentaCorriente, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<CuentaCorrienteClienteModel> slice = cuentaCorrienteClienteDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente.");
        return slice;
    }

    @Override
    public Slice<CuentaCorrienteClienteModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades CuentaCorriente, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<CuentaCorrienteClienteModel> slice = cuentaCorrienteClienteDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = cuentaCorrienteClienteDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades CuentaCorriente.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = cuentaCorrienteClienteDAO.count();
        log.info("Existen {} entidades CuentaCorriente, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public CuentaCorrienteClienteModel crear(CuentaCorrienteClienteModel model) {
        log.info("Insertando la entidad CuentaCorrienteModel: {}.",  model);
        CuentaCorrienteClienteModel cuentaCorrienteClienteModel = cuentaCorrienteClienteDAO.save(model);
        if (model.getId() == null) {
            cuentaCorrienteClienteModel.setCreada(Helper.getNow(""));
            cuentaCorrienteClienteModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad CuentaCorrienteModel.");
        } else {
            cuentaCorrienteClienteModel.setModificada(Helper.getNow(""));
            cuentaCorrienteClienteModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad CuentaCorrienteModel.");
        }
        return cuentaCorrienteClienteDAO.save(cuentaCorrienteClienteModel);
    }

    @Override
    public CuentaCorrienteClienteModel guardar(AbsCuentaCorrienteCreation creation) {
        log.info("Insertando la entidad CuentaCorrienteCreation: {}.",  creation);
        CuentaCorrienteClienteModel cuentaCorrienteClienteModel = cuentaCorrienteClienteDAO.save(cuentaCorrienteClienteMapper.toEntity(creation));
        if (creation.getId() == null) {
            cuentaCorrienteClienteModel.setCreada(Helper.getNow(""));
            cuentaCorrienteClienteModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad CuentaCorrienteCreation.");
        } else {
            cuentaCorrienteClienteModel.setModificada(Helper.getNow(""));
            cuentaCorrienteClienteModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad CuentaCorrienteCreation.");
        }
        return cuentaCorrienteClienteDAO.save(cuentaCorrienteClienteModel);
    }

    @Override
    public CuentaCorrienteClienteModel eliminar(Long id) {
        log.info("Eliminando la entidad CuentaCorriente con id: {}.", id);
        CuentaCorrienteClienteModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad CuentaCorriente con id: " + id + ", fue eliminada correctamente.");
        return cuentaCorrienteClienteDAO.save(objeto);
    }

    @Override
    public CuentaCorrienteClienteModel reciclar(Long id) {
        log.info("Reciclando la entidad CuentaCorriente con id: {}.", id);
        CuentaCorrienteClienteModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad CuentaCorriente con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad CuentaCorriente con id: " + id + ", fue reciclada correctamente.");
        return cuentaCorrienteClienteDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad CuentaCorriente con id: {}.", id);
        CuentaCorrienteClienteModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad CuentaCorriente con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        cuentaCorrienteClienteDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
