package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.MovimientoMapper;
import gloit.hiperionida.helios.mapper.creation.MovimientoCreation;
import gloit.hiperionida.helios.model.MovimientoModel;
import gloit.hiperionida.helios.repository.MovimientoDAO;
import gloit.hiperionida.helios.service.MovimientoService;
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
public class MovimientoServiceImpl implements MovimientoService {
    private final MovimientoDAO movimientoDAO;
    private final MovimientoMapper movimientoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public MovimientoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Movimiento con id: {}.", id);
        MovimientoModel movimientoModel = movimientoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Movimiento con id: " + id + "."));
        String mensaje = "Se encontro una entidad Movimiento.";
        log.info(mensaje);
        return movimientoModel;
    }

    @Override
    public MovimientoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Movimiento con id: {}, incluidas las eliminadas.", id);
        MovimientoModel movimientoModel = movimientoDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Movimiento con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Movimiento con id: " + id + ".");
        return movimientoModel;
    }

    @Override
    public List<MovimientoModel> buscarTodas() {
        log.info("Buscando todas las entidades Movimiento.");
        List<MovimientoModel> listado = movimientoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Movimiento.");
        return listado;
    }

    @Override
    public List<MovimientoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Movimiento, incluidas las eliminadas.");
        List<MovimientoModel> listado = movimientoDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Movimiento, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = movimientoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Movimiento.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = movimientoDAO.count();
        log.info("Existen {} entidades Movimiento, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public MovimientoModel guardar(MovimientoCreation creation) {
        log.info("Insertando la entidad Movimiento: {}.",  creation);
        MovimientoModel movimientoModel = movimientoDAO.save(movimientoMapper.toEntity(creation));
        if (creation.getId() == null) {
            movimientoModel.setCreada(Helper.getNow(""));
            movimientoModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            movimientoModel.setModificada(Helper.getNow(""));
            movimientoModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return movimientoDAO.save(movimientoModel);
    }

    @Override
    public MovimientoModel eliminar(Long id) {
        log.info("Eliminando la entidad Movimiento con id: {}.", id);
        MovimientoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Movimiento con id: " + id + ", fue eliminada correctamente.");
        return movimientoDAO.save(objeto);
    }

    @Override
    public MovimientoModel reciclar(Long id) {
        log.info("Reciclando la entidad Movimiento con id: {}.", id);
        MovimientoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Movimiento con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Movimiento con id: " + id + ", fue reciclada correctamente.");
        return movimientoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Movimiento con id: {}.", id);
        MovimientoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Movimiento con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        movimientoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
