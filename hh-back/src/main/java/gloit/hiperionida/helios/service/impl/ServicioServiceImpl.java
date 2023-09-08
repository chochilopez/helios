package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.ServicioMapper;
import gloit.hiperionida.helios.mapper.creation.ServicioCreation;
import gloit.hiperionida.helios.model.ServicioModel;
import gloit.hiperionida.helios.repository.ServicioDAO;
import gloit.hiperionida.helios.service.ServicioService;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.CustomDataNotFoundException;
import gloit.hiperionida.helios.util.exception.CustomObjectNotDeletedException;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServicioServiceImpl implements ServicioService {
    private final ServicioDAO servicioDAO;
    private final ServicioMapper servicioMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public ServicioModel buscarPorId(Long id) {
        log.info("Buscando la entidad Servicio con id: {}.", id);
        ServicioModel servicioModel = servicioDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Servicio con id: " + id + "."));
        String mensaje = "Se encontro una entidad Servicio.";
        log.info(mensaje);
        return servicioModel;
    }

    @Override
    public ServicioModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Servicio con id: {}, incluidas las eliminadas.", id);
        ServicioModel servicioModel = servicioDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Servicio con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Servicio con id: " + id + ".");
        return servicioModel;
    }

    @Override
    public List<ServicioModel> buscarTodas() {
        log.info("Buscando todas las entidades Servicio.");
        List<ServicioModel> listado = servicioDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Servicio.");
        return listado;
    }

    @Override
    public List<ServicioModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Servicio, incluidas las eliminadas.");
        List<ServicioModel> listado = servicioDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Servicio, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = servicioDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Servicio.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = servicioDAO.count();
        log.info("Existen {} entidades Servicio, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ServicioModel guardar(ServicioCreation creation) {
        log.info("Insertando la entidad Servicio: {}.",  creation);
        ServicioModel servicioModel = servicioDAO.save(servicioMapper.toEntity(creation));
        if (creation.getId() == null) {
            servicioModel.setCreada(Helper.getNow(""));
            servicioModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            servicioModel.setModificada(Helper.getNow(""));
            servicioModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return servicioDAO.save(servicioModel);
    }

    @Override
    public ServicioModel eliminar(Long id) {
        log.info("Eliminando la entidad Servicio con id: {}.", id);
        ServicioModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Servicio con id: " + id + ", fue eliminada correctamente.");
        return servicioDAO.save(objeto);
    }

    @Override
    public ServicioModel reciclar(Long id) {
        log.info("Reciclando la entidad Servicio con id: {}.", id);
        ServicioModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Servicio con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Servicio con id: " + id + ", fue reciclada correctamente.");
        return servicioDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Servicio con id: {}.", id);
        ServicioModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Servicio con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        servicioDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
