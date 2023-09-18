package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.VisitaMapper;
import gloit.hiperionida.helios.mapper.creation.VisitaCreation;
import gloit.hiperionida.helios.model.VisitaModel;
import gloit.hiperionida.helios.repository.VisitaDAO;
import gloit.hiperionida.helios.service.VisitaService;
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
public class VisitaServiceImpl implements VisitaService {
    private final VisitaDAO visitaDAO;
    private final VisitaMapper visitaMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public VisitaModel buscarPorId(Long id) {
        log.info("Buscando la entidad Visita con id: {}.", id);
        VisitaModel visitaModel = visitaDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Visita con id: " + id + "."));
        String mensaje = "Se encontro una entidad Visita.";
        log.info(mensaje);
        return visitaModel;
    }

    @Override
    public VisitaModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Visita con id: {}, incluidas las eliminadas.", id);
        VisitaModel visitaModel = visitaDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Visita con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Visita con id: " + id + ".");
        return visitaModel;
    }

    @Override
    public List<VisitaModel> buscarTodas() {
        log.info("Buscando todas las entidades Visita.");
        List<VisitaModel> listado = visitaDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Visita.");
        return listado;
    }

    @Override
    public List<VisitaModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Visita, incluidas las eliminadas.");
        List<VisitaModel> listado = visitaDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Visita, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<VisitaModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Visita, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<VisitaModel> slice = visitaDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Visita.");
        return slice;
    }

    @Override
    public Slice<VisitaModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Visita, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<VisitaModel> slice = visitaDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Visita, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = visitaDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Visita.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = visitaDAO.count();
        log.info("Existen {} entidades Visita, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public VisitaModel guardar(VisitaCreation creation) {
        log.info("Insertando la entidad Visita: {}.",  creation);
        VisitaModel visitaModel = visitaDAO.save(visitaMapper.toEntity(creation));
        if (creation.getId() == null) {
            visitaModel.setCreada(Helper.getNow(""));
            visitaModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            visitaModel.setModificada(Helper.getNow(""));
            visitaModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return visitaDAO.save(visitaModel);
    }

    @Override
    public VisitaModel eliminar(Long id) {
        log.info("Eliminando la entidad Visita con id: {}.", id);
        VisitaModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Visita con id: " + id + ", fue eliminada correctamente.");
        return visitaDAO.save(objeto);
    }

    @Override
    public VisitaModel reciclar(Long id) {
        log.info("Reciclando la entidad Visita con id: {}.", id);
        VisitaModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Visita con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Visita con id: " + id + ", fue reciclada correctamente.");
        return visitaDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Visita con id: {}.", id);
        VisitaModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Visita con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        visitaDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
