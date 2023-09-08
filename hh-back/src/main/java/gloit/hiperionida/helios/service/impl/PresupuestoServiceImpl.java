package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.PresupuestoMapper;
import gloit.hiperionida.helios.mapper.creation.PresupuestoCreation;
import gloit.hiperionida.helios.model.PresupuestoModel;
import gloit.hiperionida.helios.repository.PresupuestoDAO;
import gloit.hiperionida.helios.service.PresupuestoService;
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
public class PresupuestoServiceImpl implements PresupuestoService {
    private final PresupuestoDAO presupuestoDAO;
    private final PresupuestoMapper presupuestoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public PresupuestoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Presupuesto con id: {}.", id);
        PresupuestoModel presupuestoModel = presupuestoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Presupuesto con id: " + id + "."));
        String mensaje = "Se encontro una entidad Presupuesto.";
        log.info(mensaje);
        return presupuestoModel;
    }

    @Override
    public PresupuestoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Presupuesto con id: {}, incluidas las eliminadas.", id);
        PresupuestoModel presupuestoModel = presupuestoDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Presupuesto con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Presupuesto con id: " + id + ".");
        return presupuestoModel;
    }

    @Override
    public List<PresupuestoModel> buscarTodas() {
        log.info("Buscando todas las entidades Presupuesto.");
        List<PresupuestoModel> listado = presupuestoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Presupuesto.");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Presupuesto, incluidas las eliminadas.");
        List<PresupuestoModel> listado = presupuestoDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Presupuesto, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = presupuestoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Presupuesto.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = presupuestoDAO.count();
        log.info("Existen {} entidades Presupuesto, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public PresupuestoModel guardar(PresupuestoCreation creation) {
        log.info("Insertando la entidad Presupuesto: {}.",  creation);
        PresupuestoModel presupuestoModel = presupuestoDAO.save(presupuestoMapper.toEntity(creation));
        if (creation.getId() == null) {
            presupuestoModel.setCreada(Helper.getNow(""));
            presupuestoModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            presupuestoModel.setModificada(Helper.getNow(""));
            presupuestoModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return presupuestoDAO.save(presupuestoModel);
    }

    @Override
    public PresupuestoModel eliminar(Long id) {
        log.info("Eliminando la entidad Presupuesto con id: {}.", id);
        PresupuestoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Presupuesto con id: " + id + ", fue eliminada correctamente.");
        return presupuestoDAO.save(objeto);
    }

    @Override
    public PresupuestoModel reciclar(Long id) {
        log.info("Reciclando la entidad Presupuesto con id: {}.", id);
        PresupuestoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Presupuesto con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Presupuesto con id: " + id + ", fue reciclada correctamente.");
        return presupuestoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Presupuesto con id: {}.", id);
        PresupuestoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Presupuesto con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        presupuestoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
