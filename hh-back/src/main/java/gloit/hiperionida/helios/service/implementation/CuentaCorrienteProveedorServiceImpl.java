package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.CuentaCorrienteClienteMapper;
import gloit.hiperionida.helios.mapper.creation.CuentaCorrienteProveedorCreation;
import gloit.hiperionida.helios.mapper.dto.CuentaCorrienteProveedorDTO;
import gloit.hiperionida.helios.model.CuentaCorrienteProveedorModel;
import gloit.hiperionida.helios.repository.CuentaCorrienteProveedorDAO;
import gloit.hiperionida.helios.service.CuentaCorrienteProveedorService;
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
public class CuentaCorrienteProveedorServiceImpl implements CuentaCorrienteProveedorService {
    private final CuentaCorrienteProveedorDAO cuentaCorrienteProveedorDAO;
    private final CuentaCorrienteClienteMapper cuentaCorrienteClienteMapper;
    private final ReciboServiceImpl reciboService;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public List<CuentaCorrienteProveedorDTO> calcularSaldo(List<CuentaCorrienteProveedorDTO> listado) {
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
    public List<CuentaCorrienteProveedorModel> buscarTodasPorProveedorId(Long id) {
        log.info("Buscando todas las entidades CuentaCorriente con factura id: {}.", id);
        List<CuentaCorrienteProveedorModel> listado = cuentaCorrienteProveedorDAO.findAllByProveedorIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente con factura id: " + id + ".");
        return listado;
    }

    @Override
    public List<CuentaCorrienteProveedorModel> buscarTodasPorProveedorIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades CuentaCorriente con factura id: {}, incluidas las eliminadas.", id);
        List<CuentaCorrienteProveedorModel> listado = cuentaCorrienteProveedorDAO.findAllByProveedorId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente con factura id: " + id + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public CuentaCorrienteProveedorModel buscarPorId(Long id) {
        log.info("Buscando la entidad CuentaCorriente con id: {}.", id);
        CuentaCorrienteProveedorModel cuentaCorrienteProveedorModel = cuentaCorrienteProveedorDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad CuentaCorriente con id: " + id + "."));
        log.info("Se encontró una entidad CuentaCorriente con id: " + id + ".");
        return cuentaCorrienteProveedorModel;
    }

    @Override
    public CuentaCorrienteProveedorModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad CuentaCorriente con id: {}, incluidas las eliminadas.", id);
        CuentaCorrienteProveedorModel cuentaCorrienteProveedorModel = cuentaCorrienteProveedorDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad CuentaCorriente con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad CuentaCorriente con id: " + id + ", incluidas las eliminadas.");
        return cuentaCorrienteProveedorModel;
    }

    @Override
    public List<CuentaCorrienteProveedorModel> buscarTodas() {
        log.info("Buscando todas las entidades CuentaCorriente.");
        List<CuentaCorrienteProveedorModel> listado = cuentaCorrienteProveedorDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente.");
        return listado;
    }

    @Override
    public List<CuentaCorrienteProveedorModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades CuentaCorriente, incluidas las eliminadas.");
        List<CuentaCorrienteProveedorModel> listado = cuentaCorrienteProveedorDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<CuentaCorrienteProveedorModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades CuentaCorriente, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<CuentaCorrienteProveedorModel> slice = cuentaCorrienteProveedorDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente.");
        return slice;
    }

    @Override
    public Slice<CuentaCorrienteProveedorModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades CuentaCorriente, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<CuentaCorrienteProveedorModel> slice = cuentaCorrienteProveedorDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = cuentaCorrienteProveedorDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades CuentaCorriente.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = cuentaCorrienteProveedorDAO.count();
        log.info("Existen {} entidades CuentaCorriente, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public CuentaCorrienteProveedorModel crear(CuentaCorrienteProveedorModel model) {
        log.info("Insertando la entidad CuentaCorrienteModel: {}.",  model);
        CuentaCorrienteProveedorModel cuentaCorrienteProveedorModel = cuentaCorrienteProveedorDAO.save(model);
        if (model.getId() == null) {
            cuentaCorrienteProveedorModel.setCreada(Helper.getNow(""));
            cuentaCorrienteProveedorModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad CuentaCorrienteModel.");
        } else {
            cuentaCorrienteProveedorModel.setModificada(Helper.getNow(""));
            cuentaCorrienteProveedorModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad CuentaCorrienteModel.");
        }
        return cuentaCorrienteProveedorDAO.save(cuentaCorrienteProveedorModel);
    }

    @Override
    public CuentaCorrienteProveedorModel guardar(CuentaCorrienteProveedorCreation creation) {
        log.info("Insertando la entidad CuentaCorrienteProveedorCreation: {}.",  creation);
        CuentaCorrienteProveedorModel cuentaCorrienteProveedorModel = cuentaCorrienteProveedorDAO.save(cuentaCorrienteClienteMapper.toEntity(creation));
        if (creation.getId() == null) {
            cuentaCorrienteProveedorModel.setCreada(Helper.getNow(""));
            cuentaCorrienteProveedorModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad CuentaCorrienteProveedorCreation.");
        } else {
            cuentaCorrienteProveedorModel.setModificada(Helper.getNow(""));
            cuentaCorrienteProveedorModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad CuentaCorrienteProveedorCreation.");
        }
        return cuentaCorrienteProveedorDAO.save(cuentaCorrienteProveedorModel);
    }

    @Override
    public CuentaCorrienteProveedorModel eliminar(Long id) {
        log.info("Eliminando la entidad CuentaCorriente con id: {}.", id);
        CuentaCorrienteProveedorModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad CuentaCorriente con id: " + id + ", fue eliminada correctamente.");
        return cuentaCorrienteProveedorDAO.save(objeto);
    }

    @Override
    public CuentaCorrienteProveedorModel reciclar(Long id) {
        log.info("Reciclando la entidad CuentaCorriente con id: {}.", id);
        CuentaCorrienteProveedorModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad CuentaCorriente con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad CuentaCorriente con id: " + id + ", fue reciclada correctamente.");
        return cuentaCorrienteProveedorDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad CuentaCorriente con id: {}.", id);
        CuentaCorrienteProveedorModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad CuentaCorriente con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        cuentaCorrienteProveedorDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
