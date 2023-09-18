package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.AdelantoMapper;
import gloit.hiperionida.helios.mapper.creation.AdelantoCreation;
import gloit.hiperionida.helios.model.AdelantoModel;
import gloit.hiperionida.helios.repository.AdelantoDAO;
import gloit.hiperionida.helios.service.AdelantoService;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.CustomDataNotFoundException;
import gloit.hiperionida.helios.util.exception.CustomObjectNotDeletedException;
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
public class AdelantoServiceImpl implements AdelantoService {
    private final AdelantoDAO adelantoDAO;
    private final AdelantoMapper adelantoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public AdelantoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Adelanto con id: {}.", id);
        AdelantoModel adelantoModel = adelantoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Adelanto con id: " + id + "."));
        String mensaje = "Se encontro una entidad Adelanto.";
        log.info(mensaje);
        return adelantoModel;
    }

    @Override
    public AdelantoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Adelanto con id: {}, incluidas las eliminadas.", id);
        AdelantoModel adelantoModel = adelantoDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Adelanto con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Adelanto con id: " + id + ".");
        return adelantoModel;
    }

    @Override
    public List<AdelantoModel> buscarTodas() {
        log.info("Buscando todas las entidades Adelanto.");
        List<AdelantoModel> listado = adelantoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Adelanto.");
        return listado;
    }

    @Override
    public List<AdelantoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Adelanto, incluidas las eliminadas.");
        List<AdelantoModel> listado = adelantoDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Adelanto, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<AdelantoModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Adelanto, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<AdelantoModel> slice = adelantoDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Adelanto.");
        return slice;
    }

    @Override
    public Slice<AdelantoModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Adelanto, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<AdelantoModel> slice = adelantoDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Adelanto, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = adelantoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Adelanto.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = adelantoDAO.count();
        log.info("Existen {} entidades Adelanto, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public AdelantoModel guardar(AdelantoCreation creation) {
        log.info("Insertando la entidad Adelanto: {}.",  creation);
        AdelantoModel adelantoModel = adelantoDAO.save(adelantoMapper.toEntity(creation));
        if (creation.getId() == null) {
            adelantoModel.setCreada(Helper.getNow(""));
            adelantoModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            adelantoModel.setModificada(Helper.getNow(""));
            adelantoModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return adelantoDAO.save(adelantoModel);
    }

    @Override
    public AdelantoModel eliminar(Long id) {
        log.info("Eliminando la entidad Adelanto con id: {}.", id);
        AdelantoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Adelanto con id: " + id + ", fue eliminada correctamente.");
        return adelantoDAO.save(objeto);
    }

    @Override
    public AdelantoModel reciclar(Long id) {
        log.info("Reciclando la entidad Adelanto con id: {}.", id);
        AdelantoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Adelanto con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Adelanto con id: " + id + ", fue reciclada correctamente.");
        return adelantoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Adelanto con id: {}.", id);
        AdelantoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Adelanto con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        adelantoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
