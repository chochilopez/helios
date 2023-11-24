package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.RemitoMapper;
import gloit.hiperionida.helios.mapper.creation.RemitoCreation;
import gloit.hiperionida.helios.mapper.creation.RemitoCreation;
import gloit.hiperionida.helios.model.RemitoModel;
import gloit.hiperionida.helios.model.RemitoModel;
import gloit.hiperionida.helios.repository.RemitoDAO;
import gloit.hiperionida.helios.service.RemitoService;
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
public class RemitoServiceImpl implements RemitoService {
    private final RemitoDAO remitoDAO;
    private final RemitoMapper remitoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public RemitoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Remito con id: {}.", id);
        RemitoModel remitoModel = remitoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Remito con id: " + id + "."));
        log.info("Se encontró una entidad Remito con id: " + id + ".");
        return remitoModel;
    }

    @Override
    public RemitoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Remito con id: {}, incluidas las eliminadas.", id);
        RemitoModel remitoModel = remitoDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Remito con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Remito con id: " + id + ", incluidas las eliminadas.");
        return remitoModel;
    }

    @Override
    public List<RemitoModel> buscarTodas() {
        log.info("Buscando todas las entidades Remito.");
        List<RemitoModel> listado = remitoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Remito.");
        return listado;
    }

    @Override
    public List<RemitoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Remito, incluidas las eliminadas.");
        List<RemitoModel> listado = remitoDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Remito, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<RemitoModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Remito, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<RemitoModel> slice = remitoDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Remito.");
        return slice;
    }

    @Override
    public Slice<RemitoModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Remito, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<RemitoModel> slice = remitoDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Remito, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = remitoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Remito.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = remitoDAO.count();
        log.info("Existen {} entidades Remito, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public RemitoModel crear(RemitoModel model) {
        log.info("Insertando la entidad RemitoModel: {}.",  model);
        RemitoModel remitoModel = remitoDAO.save(model);
        if (model.getId() == null) {
            remitoModel.setCreada(Helper.getNow(""));
            remitoModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad RemitoModel.");
        } else {
            remitoModel.setModificada(Helper.getNow(""));
            remitoModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad RemitoModel.");
        }
        return remitoDAO.save(remitoModel);
    }

    @Override
    public RemitoModel guardar(RemitoCreation creation) {
        log.info("Insertando la entidad RemitoCreation: {}.",  creation);
        RemitoModel remitoModel = remitoDAO.save(remitoMapper.toEntity(creation));
        if (creation.getId() == null) {
            remitoModel.setCreada(Helper.getNow(""));
            remitoModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad RemitoCreation.");
        } else {
            remitoModel.setModificada(Helper.getNow(""));
            remitoModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad RemitoCreation.");
        }
        return remitoDAO.save(remitoModel);
    }

    @Override
    public RemitoModel eliminar(Long id) {
        log.info("Eliminando la entidad Remito con id: {}.", id);
        RemitoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Remito con id: " + id + ", fue eliminada correctamente.");
        return remitoDAO.save(objeto);
    }

    @Override
    public RemitoModel reciclar(Long id) {
        log.info("Reciclando la entidad Remito con id: {}.", id);
        RemitoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Remito con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Remito con id: " + id + ", fue reciclada correctamente.");
        return remitoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Remito con id: {}.", id);
        RemitoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Remito con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        remitoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
