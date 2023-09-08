package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.ConfiguracionMapper;
import gloit.hiperionida.helios.mapper.creation.ConfiguracionCreation;
import gloit.hiperionida.helios.model.ConfiguracionModel;
import gloit.hiperionida.helios.repository.ConfiguracionDAO;
import gloit.hiperionida.helios.service.ConfiguracionService;
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
public class ConfiguracionServiceImpl implements ConfiguracionService {
    private final ConfiguracionDAO configuracionDAO;
    private final ConfiguracionMapper configuracionMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public ConfiguracionModel buscarPorId(Long id) {
        log.info("Buscando la entidad Configuracion con id: {}.", id);
        ConfiguracionModel configuracionModel = configuracionDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Configuracion con id: " + id + "."));
        String mensaje = "Se encontro una entidad Configuracion.";
        log.info(mensaje);
        return configuracionModel;
    }

    @Override
    public ConfiguracionModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Configuracion con id: {}, incluidas las eliminadas.", id);
        ConfiguracionModel configuracionModel = configuracionDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Configuracion con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Configuracion con id: " + id + ".");
        return configuracionModel;
    }

    @Override
    public List<ConfiguracionModel> buscarTodas() {
        log.info("Buscando todas las entidades Configuracion.");
        List<ConfiguracionModel> listado = configuracionDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Configuracion.");
        return listado;
    }

    @Override
    public List<ConfiguracionModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Configuracion, incluidas las eliminadas.");
        List<ConfiguracionModel> listado = configuracionDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Configuracion, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = configuracionDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Configuracion.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = configuracionDAO.count();
        log.info("Existen {} entidades Configuracion, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ConfiguracionModel guardar(ConfiguracionCreation creation) {
        log.info("Insertando la entidad Configuracion: {}.",  creation);
        ConfiguracionModel configuracionModel = configuracionDAO.save(configuracionMapper.toEntity(creation));
        if (creation.getId() == null) {
            configuracionModel.setCreada(Helper.getNow(""));
            configuracionModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            configuracionModel.setModificada(Helper.getNow(""));
            configuracionModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return configuracionDAO.save(configuracionModel);
    }

    @Override
    public ConfiguracionModel eliminar(Long id) {
        log.info("Eliminando la entidad Configuracion con id: {}.", id);
        ConfiguracionModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Configuracion con id: " + id + ", fue eliminada correctamente.");
        return configuracionDAO.save(objeto);
    }

    @Override
    public ConfiguracionModel reciclar(Long id) {
        log.info("Reciclando la entidad Configuracion con id: {}.", id);
        ConfiguracionModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Configuracion con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Configuracion con id: " + id + ", fue reciclada correctamente.");
        return configuracionDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Configuracion con id: {}.", id);
        ConfiguracionModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Configuracion con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        configuracionDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
