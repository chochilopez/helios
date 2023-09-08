package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.GastoMapper;
import gloit.hiperionida.helios.mapper.creation.GastoCreation;
import gloit.hiperionida.helios.model.GastoModel;
import gloit.hiperionida.helios.repository.GastoDAO;
import gloit.hiperionida.helios.service.GastoService;
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
public class GastoServiceImpl implements GastoService {
    private final GastoDAO gastoDAO;
    private final GastoMapper gastoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public GastoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Gasto con id: {}.", id);
        GastoModel gastoModel = gastoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Gasto con id: " + id + "."));
        String mensaje = "Se encontro una entidad Gasto.";
        log.info(mensaje);
        return gastoModel;
    }

    @Override
    public GastoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Gasto con id: {}, incluidas las eliminadas.", id);
        GastoModel gastoModel = gastoDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Gasto con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Gasto con id: " + id + ".");
        return gastoModel;
    }

    @Override
    public List<GastoModel> buscarTodas() {
        log.info("Buscando todas las entidades Gasto.");
        List<GastoModel> listado = gastoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Gasto.");
        return listado;
    }

    @Override
    public List<GastoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Gasto, incluidas las eliminadas.");
        List<GastoModel> listado = gastoDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Gasto, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = gastoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Gasto.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = gastoDAO.count();
        log.info("Existen {} entidades Gasto, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public GastoModel guardar(GastoCreation creation) {
        log.info("Insertando la entidad Gasto: {}.",  creation);
        GastoModel gastoModel = gastoDAO.save(gastoMapper.toEntity(creation));
        if (creation.getId() == null) {
            gastoModel.setCreada(Helper.getNow(""));
            gastoModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            gastoModel.setModificada(Helper.getNow(""));
            gastoModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return gastoDAO.save(gastoModel);
    }

    @Override
    public GastoModel eliminar(Long id) {
        log.info("Eliminando la entidad Gasto con id: {}.", id);
        GastoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Gasto con id: " + id + ", fue eliminada correctamente.");
        return gastoDAO.save(objeto);
    }

    @Override
    public GastoModel reciclar(Long id) {
        log.info("Reciclando la entidad Gasto con id: {}.", id);
        GastoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Gasto con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Gasto con id: " + id + ", fue reciclada correctamente.");
        return gastoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Gasto con id: {}.", id);
        GastoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Gasto con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        gastoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
