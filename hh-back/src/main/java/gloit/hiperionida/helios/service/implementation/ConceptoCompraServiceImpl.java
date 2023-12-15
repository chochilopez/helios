package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.ConceptoCompraMapper;
import gloit.hiperionida.helios.mapper.creation.ConceptoCompraCreation;
import gloit.hiperionida.helios.model.ConceptoCompraModel;
import gloit.hiperionida.helios.repository.ConceptoCompraDAO;
import gloit.hiperionida.helios.service.ConceptoCompraService;
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
public class ConceptoCompraServiceImpl implements ConceptoCompraService {
    private final ConceptoCompraDAO conceptoCompraDAO;
    private final ConceptoCompraMapper conceptoCompraMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public ConceptoCompraModel buscarPorId(Long id) {
        log.info("Buscando la entidad ConceptoCompra con id: {}.", id);
        ConceptoCompraModel conceptoCompraModel = conceptoCompraDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad ConceptoCompra con id: " + id + "."));
        log.info("Se encontró una entidad ConceptoCompra con id: " + id + ".");
        return conceptoCompraModel;
    }

    @Override
    public ConceptoCompraModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad ConceptoCompra con id: {}, incluidas las eliminadas.", id);
        ConceptoCompraModel conceptoCompraModel = conceptoCompraDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad ConceptoCompra con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad ConceptoCompra con id: " + id + ", incluidas las eliminadas.");
        return conceptoCompraModel;
    }

    @Override
    public List<ConceptoCompraModel> buscarTodas() {
        log.info("Buscando todas las entidades ConceptoCompra.");
        List<ConceptoCompraModel> listado = conceptoCompraDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades ConceptoCompra.");
        return listado;
    }

    @Override
    public List<ConceptoCompraModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades ConceptoCompra, incluidas las eliminadas.");
        List<ConceptoCompraModel> listado = conceptoCompraDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades ConceptoCompra, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<ConceptoCompraModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades ConceptoCompra, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<ConceptoCompraModel> slice = conceptoCompraDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades ConceptoCompra.");
        return slice;
    }

    @Override
    public Slice<ConceptoCompraModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades ConceptoCompra, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<ConceptoCompraModel> slice = conceptoCompraDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades ConceptoCompra, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = conceptoCompraDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades ConceptoCompra.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = conceptoCompraDAO.count();
        log.info("Existen {} entidades ConceptoCompra, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ConceptoCompraModel crear(ConceptoCompraModel model) {
        log.info("Insertando la entidad ConceptoCompraModel: {}.",  model);
        ConceptoCompraModel conceptoCompraModel = conceptoCompraDAO.save(model);
        if (model.getId() == null) {
            conceptoCompraModel.setCreada(Helper.getNow(""));
            conceptoCompraModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad ConceptoCompraModel.");
        } else {
            conceptoCompraModel.setModificada(Helper.getNow(""));
            conceptoCompraModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad ConceptoCompraModel.");
        }
        return conceptoCompraDAO.save(conceptoCompraModel);
    }

    @Override
    public ConceptoCompraModel guardar(ConceptoCompraCreation creation) {
        log.info("Insertando la entidad ConceptoCompraCreation: {}.",  creation);
        ConceptoCompraModel conceptoCompraModel = conceptoCompraDAO.save(conceptoCompraMapper.toEntity(creation));
        if (creation.getId() == null) {
            conceptoCompraModel.setCreada(Helper.getNow(""));
            conceptoCompraModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad ConceptoCompraCreation.");
        } else {
            conceptoCompraModel.setModificada(Helper.getNow(""));
            conceptoCompraModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad ConceptoCompraCreation.");
        }
        return conceptoCompraDAO.save(conceptoCompraModel);
    }

    @Override
    public ConceptoCompraModel eliminar(Long id) {
        log.info("Eliminando la entidad ConceptoCompra con id: {}.", id);
        ConceptoCompraModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad ConceptoCompra con id: " + id + ", fue eliminada correctamente.");
        return conceptoCompraDAO.save(objeto);
    }

    @Override
    public ConceptoCompraModel reciclar(Long id) {
        log.info("Reciclando la entidad ConceptoCompra con id: {}.", id);
        ConceptoCompraModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad ConceptoCompra con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad ConceptoCompra con id: " + id + ", fue reciclada correctamente.");
        return conceptoCompraDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad ConceptoCompra con id: {}.", id);
        ConceptoCompraModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad ConceptoCompra con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        conceptoCompraDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
