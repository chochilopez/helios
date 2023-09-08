package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.RemitoMapper;
import gloit.hiperionida.helios.mapper.creation.RemitoCreation;
import gloit.hiperionida.helios.model.RemitoModel;
import gloit.hiperionida.helios.repository.RemitoDAO;
import gloit.hiperionida.helios.service.RemitoService;
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
public class RemitoServiceImpl implements RemitoService {
    private final RemitoDAO remitoDAO;
    private final RemitoMapper remitoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public RemitoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Remito con id: {}.", id);
        RemitoModel remitoModel = remitoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Remito con id: " + id + "."));
        String mensaje = "Se encontro una entidad Remito.";
        log.info(mensaje);
        return remitoModel;
    }

    @Override
    public RemitoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Remito con id: {}, incluidas las eliminadas.", id);
        RemitoModel remitoModel = remitoDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Remito con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Remito con id: " + id + ".");
        return remitoModel;
    }

    @Override
    public List<RemitoModel> buscarTodas() {
        log.info("Buscando todas las entidades Remito.");
        List<RemitoModel> listado = remitoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Remito.");
        return listado;
    }

    @Override
    public List<RemitoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Remito, incluidas las eliminadas.");
        List<RemitoModel> listado = remitoDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Remito, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = remitoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Remito.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = remitoDAO.count();
        log.info("Existen {} entidades Remito, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public RemitoModel guardar(RemitoCreation creation) {
        log.info("Insertando la entidad Remito: {}.",  creation);
        RemitoModel remitoModel = remitoDAO.save(remitoMapper.toEntity(creation));
        if (creation.getId() == null) {
            remitoModel.setCreada(Helper.getNow(""));
            remitoModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            remitoModel.setModificada(Helper.getNow(""));
            remitoModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return remitoDAO.save(remitoModel);
    }

    @Override
    public RemitoModel eliminar(Long id) {
        log.info("Eliminando la entidad Remito con id: {}.", id);
        RemitoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Remito con id: " + id + ", fue eliminada correctamente.");
        return remitoDAO.save(objeto);
    }

    @Override
    public RemitoModel reciclar(Long id) {
        log.info("Reciclando la entidad Remito con id: {}.", id);
        RemitoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Remito con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Remito con id: " + id + ", fue reciclada correctamente.");
        return remitoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Remito con id: {}.", id);
        RemitoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Remito con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        remitoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
