package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.CuentaCorrienteMapper;
import gloit.hiperionida.helios.mapper.creation.CuentaCorrienteCreation;
import gloit.hiperionida.helios.mapper.dto.CuentaCorrienteDTO;
import gloit.hiperionida.helios.model.CuentaCorrienteModel;
import gloit.hiperionida.helios.repository.CuentaCorrienteDAO;
import gloit.hiperionida.helios.service.CuentaCorrienteService;
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
public class CuentaCorrienteServiceImpl implements CuentaCorrienteService {
    private final CuentaCorrienteDAO cuentaCorrienteDAO;
    private final CuentaCorrienteMapper cuentaCorrienteMapper;
    private final UsuarioServiceImpl usuarioService;

    public List<CuentaCorrienteDTO> calcularSaldo(List<CuentaCorrienteDTO> listado) {
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
                if (Objects.equals(listado.get(a - 1).getTipoMovimiento(), "DEBITO")) {
                    Double valor1 = Helper.getDecimal(listado.get(a - 1).getSaldo());
                    Double valor2 = Helper.getDecimal(listado.get(a).getMonto()) * -1;
                    Double valor3 = 0.0;
                    if (Objects.equals(listado.get(a).getTipoMovimiento(), "DEBITO")) {
                        valor3 = valor1 - valor2;
                    } else if (Objects.equals(listado.get(a).getTipoMovimiento(), "CREDITO")) {
                        valor3 = valor1 + valor2;
                    }
                    listado.get(a).setSaldo(valor3.toString());
                } else if (Objects.equals(listado.get(a - 1).getTipoMovimiento(), "CREDITO")) {
                    Double valor1 = Helper.getDecimal(listado.get(a - 1).getSaldo());
                    Double valor2 = Helper.getDecimal(listado.get(a).getMonto());
                    Double valor3 = 0.0;
                    if (Objects.equals(listado.get(a).getTipoMovimiento(), "DEBITO")) {
                        valor3 = valor1 - valor2;
                    } else if (Objects.equals(listado.get(a).getTipoMovimiento(), "CREDITO")) {
                        valor3 = valor1 + valor2;
                    }
                    listado.get(a).setSaldo(valor3.toString());
                }
            }
        }
        return listado;
    }

    @Override
    public List<CuentaCorrienteModel> buscarTodasPorClienteId(Long id) {
        log.info("Buscando todas las entidades CuentaCorriente con cliente id: {}.", id);
        List<CuentaCorrienteModel> listado = cuentaCorrienteDAO.findAllByClienteIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente con cliente id: " + id + ".");
        return listado;
    }

    @Override
    public List<CuentaCorrienteModel> buscarTodasPorClienteIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades CuentaCorriente con cliente id: {}, incluidas las eliminadas.", id);
        List<CuentaCorrienteModel> listado = cuentaCorrienteDAO.findAllByClienteId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente con cliente id: " + id + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public CuentaCorrienteModel buscarPorId(Long id) {
        log.info("Buscando la entidad CuentaCorriente con id: {}.", id);
        CuentaCorrienteModel cuentaCorrienteModel = cuentaCorrienteDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad CuentaCorriente con id: " + id + "."));
        log.info("Se encontró una entidad CuentaCorriente con id: " + id + ".");
        return cuentaCorrienteModel;
    }

    @Override
    public CuentaCorrienteModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad CuentaCorriente con id: {}, incluidas las eliminadas.", id);
        CuentaCorrienteModel cuentaCorrienteModel = cuentaCorrienteDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad CuentaCorriente con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad CuentaCorriente con id: " + id + ", incluidas las eliminadas.");
        return cuentaCorrienteModel;
    }

    @Override
    public List<CuentaCorrienteModel> buscarTodas() {
        log.info("Buscando todas las entidades CuentaCorriente.");
        List<CuentaCorrienteModel> listado = cuentaCorrienteDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente.");
        return listado;
    }

    @Override
    public List<CuentaCorrienteModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades CuentaCorriente, incluidas las eliminadas.");
        List<CuentaCorrienteModel> listado = cuentaCorrienteDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<CuentaCorrienteModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades CuentaCorriente, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<CuentaCorrienteModel> slice = cuentaCorrienteDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente.");
        return slice;
    }

    @Override
    public Slice<CuentaCorrienteModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades CuentaCorriente, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<CuentaCorrienteModel> slice = cuentaCorrienteDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CuentaCorriente, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = cuentaCorrienteDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades CuentaCorriente.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = cuentaCorrienteDAO.count();
        log.info("Existen {} entidades CuentaCorriente, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public CuentaCorrienteModel guardar(CuentaCorrienteCreation creation) {
        log.info("Insertando la entidad CuentaCorriente: {}.",  creation);
        CuentaCorrienteModel cuentaCorrienteModel = cuentaCorrienteDAO.save(cuentaCorrienteMapper.toEntity(creation));
        if (creation.getId() == null) {
            cuentaCorrienteModel.setCreada(Helper.getNow(""));
            cuentaCorrienteModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            cuentaCorrienteModel.setModificada(Helper.getNow(""));
            cuentaCorrienteModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return cuentaCorrienteDAO.save(cuentaCorrienteModel);
    }

    @Override
    public CuentaCorrienteModel eliminar(Long id) {
        log.info("Eliminando la entidad CuentaCorriente con id: {}.", id);
        CuentaCorrienteModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad CuentaCorriente con id: " + id + ", fue eliminada correctamente.");
        return cuentaCorrienteDAO.save(objeto);
    }

    @Override
    public CuentaCorrienteModel reciclar(Long id) {
        log.info("Reciclando la entidad CuentaCorriente con id: {}.", id);
        CuentaCorrienteModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad CuentaCorriente con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad CuentaCorriente con id: " + id + ", fue reciclada correctamente.");
        return cuentaCorrienteDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad CuentaCorriente con id: {}.", id);
        CuentaCorrienteModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad CuentaCorriente con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        cuentaCorrienteDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
