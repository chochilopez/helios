package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.EventoMapper;
import gloit.hiperionida.helios.mapper.creation.EventoCreation;
import gloit.hiperionida.helios.model.EventoModel;
import gloit.hiperionida.helios.repository.EventoDAO;
import gloit.hiperionida.helios.service.EventoService;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.CustomDataNotFoundException;
import gloit.hiperionida.helios.util.exception.CustomObjectNotDeletedException;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        EventoModel eventoModel = eventoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Evento con id: " + id + "."));
        String mensaje = "Se encontro una entidad Evento.";
        log.info(mensaje);
        return eventoModel;
    }

    @Override
    public EventoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Evento con id: {}, incluidas las eliminadas.", id);
        EventoModel eventoModel = eventoDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Evento con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Evento con id: " + id + ".");
        return eventoModel;
    }

    @Override
    public List<EventoModel> buscarTodas() {
        log.info("Buscando todas las entidades Evento.");
        List<EventoModel> listado = eventoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Evento.");
        return listado;
    }

    @Override
    public List<EventoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Evento, incluidas las eliminadas.");
        List<EventoModel> listado = eventoDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Evento, incluidas las eliminadas.");
        return listado;
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
            eventoModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            eventoModel.setModificada(Helper.getNow(""));
            eventoModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return eventoDAO.save(eventoModel);
    }

    @Override
    public EventoModel eliminar(Long id) {
        log.info("Eliminando la entidad Evento con id: {}.", id);
        EventoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Evento con id: " + id + ", fue eliminada correctamente.");
        return eventoDAO.save(objeto);
    }

    @Override
    public EventoModel reciclar(Long id) {
        log.info("Reciclando la entidad Evento con id: {}.", id);
        EventoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Evento con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Evento con id: " + id + ", fue reciclada correctamente.");
        return eventoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Evento con id: {}.", id);
        EventoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Evento con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        eventoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
