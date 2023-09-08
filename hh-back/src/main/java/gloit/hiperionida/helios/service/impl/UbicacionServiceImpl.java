package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.UbicacionMapper;
import gloit.hiperionida.helios.mapper.creation.UbicacionCreation;
import gloit.hiperionida.helios.model.UbicacionModel;
import gloit.hiperionida.helios.repository.UbicacionDAO;
import gloit.hiperionida.helios.service.UbicacionService;
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
public class UbicacionServiceImpl implements UbicacionService {
    private final UbicacionDAO ubicacionDAO;
    private final UbicacionMapper ubicacionMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public UbicacionModel buscarPorId(Long id) {
        log.info("Buscando la entidad Ubicacion con id: {}.", id);
        UbicacionModel ubicacionModel = ubicacionDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Ubicacion con id: " + id + "."));
        String mensaje = "Se encontro una entidad Ubicacion.";
        log.info(mensaje);
        return ubicacionModel;
    }

    @Override
    public UbicacionModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Ubicacion con id: {}, incluidas las eliminadas.", id);
        UbicacionModel ubicacionModel = ubicacionDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Ubicacion con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Ubicacion con id: " + id + ".");
        return ubicacionModel;
    }

    @Override
    public List<UbicacionModel> buscarTodas() {
        log.info("Buscando todas las entidades Ubicacion.");
        List<UbicacionModel> listado = ubicacionDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Ubicacion.");
        return listado;
    }

    @Override
    public List<UbicacionModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Ubicacion, incluidas las eliminadas.");
        List<UbicacionModel> listado = ubicacionDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Ubicacion, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = ubicacionDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Ubicacion.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = ubicacionDAO.count();
        log.info("Existen {} entidades Ubicacion, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public UbicacionModel guardar(UbicacionCreation creation) {
        log.info("Insertando la entidad Ubicacion: {}.",  creation);
        UbicacionModel ubicacionModel = ubicacionDAO.save(ubicacionMapper.toEntity(creation));
        if (creation.getId() == null) {
            ubicacionModel.setCreada(Helper.getNow(""));
            ubicacionModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            ubicacionModel.setModificada(Helper.getNow(""));
            ubicacionModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return ubicacionDAO.save(ubicacionModel);
    }

    @Override
    public UbicacionModel eliminar(Long id) {
        log.info("Eliminando la entidad Ubicacion con id: {}.", id);
        UbicacionModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Ubicacion con id: " + id + ", fue eliminada correctamente.");
        return ubicacionDAO.save(objeto);
    }

    @Override
    public UbicacionModel reciclar(Long id) {
        log.info("Reciclando la entidad Ubicacion con id: {}.", id);
        UbicacionModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Ubicacion con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Ubicacion con id: " + id + ", fue reciclada correctamente.");
        return ubicacionDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Ubicacion con id: {}.", id);
        UbicacionModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Ubicacion con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        ubicacionDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
