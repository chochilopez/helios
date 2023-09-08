package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.ViajeMapper;
import gloit.hiperionida.helios.mapper.creation.ViajeCreation;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.repository.ViajeDAO;
import gloit.hiperionida.helios.service.ViajeService;
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
public class ViajeServiceImpl implements ViajeService {
    private final ViajeDAO viajeDAO;
    private final ViajeMapper viajeMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public ViajeModel buscarPorId(Long id) {
        log.info("Buscando la entidad Viaje con id: {}.", id);
        ViajeModel viajeModel = viajeDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Viaje con id: " + id + "."));
        String mensaje = "Se encontro una entidad Viaje.";
        log.info(mensaje);
        return viajeModel;
    }

    @Override
    public ViajeModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Viaje con id: {}, incluidas las eliminadas.", id);
        ViajeModel viajeModel = viajeDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Viaje con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Viaje con id: " + id + ".");
        return viajeModel;
    }

    @Override
    public List<ViajeModel> buscarTodas() {
        log.info("Buscando todas las entidades Viaje.");
        List<ViajeModel> listado = viajeDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Viaje.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Viaje, incluidas las eliminadas.");
        List<ViajeModel> listado = viajeDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Viaje, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = viajeDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Viaje.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = viajeDAO.count();
        log.info("Existen {} entidades Viaje, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ViajeModel guardar(ViajeCreation creation) {
        log.info("Insertando la entidad Viaje: {}.",  creation);
        ViajeModel viajeModel = viajeDAO.save(viajeMapper.toEntity(creation));
        if (creation.getId() == null) {
            viajeModel.setCreada(Helper.getNow(""));
            viajeModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            viajeModel.setModificada(Helper.getNow(""));
            viajeModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return viajeDAO.save(viajeModel);
    }

    @Override
    public ViajeModel eliminar(Long id) {
        log.info("Eliminando la entidad Viaje con id: {}.", id);
        ViajeModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Viaje con id: " + id + ", fue eliminada correctamente.");
        return viajeDAO.save(objeto);
    }

    @Override
    public ViajeModel reciclar(Long id) {
        log.info("Reciclando la entidad Viaje con id: {}.", id);
        ViajeModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Viaje con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Viaje con id: " + id + ", fue reciclada correctamente.");
        return viajeDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Viaje con id: {}.", id);
        ViajeModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Viaje con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        viajeDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
