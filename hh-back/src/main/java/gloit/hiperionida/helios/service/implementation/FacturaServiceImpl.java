package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.FacturaMapper;
import gloit.hiperionida.helios.mapper.creation.FacturaCreation;
import gloit.hiperionida.helios.model.FacturaModel;
import gloit.hiperionida.helios.repository.FacturaDAO;
import gloit.hiperionida.helios.service.FacturaService;
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

@Service
@Slf4j
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {
    private final FacturaDAO facturaDAO;
    private final FacturaMapper facturaMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public FacturaModel buscarPorId(Long id) {
        log.info("Buscando la entidad Factura con id: {}.", id);
        FacturaModel facturaModel = facturaDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Factura con id: " + id + "."));
        log.info("Se encontro una entidad Factura con id: " + id + ".");
        return facturaModel;
    }

    @Override
    public FacturaModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Factura con id: {}, incluidas las eliminadas.", id);
        FacturaModel facturaModel = facturaDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Factura con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Factura con id: " + id + ", incluidas las eliminadas.");
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
    public FacturaModel guardar(FacturaCreation creation) {
        log.info("Insertando la entidad Factura: {}.",  creation);
        FacturaModel facturaModel = facturaDAO.save(facturaMapper.toEntity(creation));
        if (creation.getId() == null) {
            facturaModel.setCreada(Helper.getNow(""));
            facturaModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            facturaModel.setModificada(Helper.getNow(""));
            facturaModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
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
