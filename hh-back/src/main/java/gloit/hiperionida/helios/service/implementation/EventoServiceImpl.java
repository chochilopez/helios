package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.EventoMapper;
import gloit.hiperionida.helios.mapper.creation.EventoCreation;
import gloit.hiperionida.helios.model.EventoModel;
import gloit.hiperionida.helios.repository.EventoDAO;
import gloit.hiperionida.helios.service.EventoService;
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
public class EventoServiceImpl implements EventoService {
    private final EventoDAO eventoDAO;
    private final EventoMapper eventoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public EventoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Evento con id: {}.", id);
        EventoModel eventoModel = eventoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Evento con id: " + id + "."));
        String mensaje = "Se encontro una entidad Evento.";
        log.info(mensaje);
        return eventoModel;
    }

    @Override
    public EventoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Evento con id: {}, incluidas las eliminadas.", id);
        EventoModel eventoModel = eventoDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Evento con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Evento con id: " + id + ".");
        return eventoModel;
    }

    @Override
    public List<EventoModel> buscarTodas() {
        log.info("Buscando todas las entidades Evento.");
        List<EventoModel> listado = eventoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Evento.");
        return listado;
    }

    @Override
    public List<EventoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Evento, incluidas las eliminadas.");
        List<EventoModel> listado = eventoDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Evento, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<EventoModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Evento, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<EventoModel> slice = eventoDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Evento.");
        return slice;
    }

    @Override
    public Slice<EventoModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Evento, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<EventoModel> slice = eventoDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Evento, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = eventoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Evento.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = eventoDAO.count();
        log.info("Existen {} entidades Evento, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public EventoModel guardar(EventoCreation creation) {
        log.info("Insertando la entidad Evento: {}.",  creation);
        EventoModel eventoModel = eventoDAO.save(eventoMapper.toEntity(creation));
        if (creation.getId() == null) {
            eventoModel.setCreada(Helper.getNow(""));
            eventoModel.setCreador_id(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            eventoModel.setModificada(Helper.getNow(""));
            eventoModel.setModificador_id(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return eventoDAO.save(eventoModel);
    }

    @Override
    public EventoModel eliminar(Long id) {
        log.info("Eliminando la entidad Evento con id: {}.", id);
        EventoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador_id(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Evento con id: " + id + ", fue eliminada correctamente.");
        return eventoDAO.save(objeto);
    }

    @Override
    public EventoModel reciclar(Long id) {
        log.info("Reciclando la entidad Evento con id: {}.", id);
        EventoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Evento con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador_id(null);
        log.info("La entidad Evento con id: " + id + ", fue reciclada correctamente.");
        return eventoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Evento con id: {}.", id);
        EventoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Evento con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        eventoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
