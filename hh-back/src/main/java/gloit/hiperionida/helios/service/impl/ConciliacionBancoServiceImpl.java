package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.ConciliacionBancoMapper;
import gloit.hiperionida.helios.mapper.creation.ConciliacionBancoCreation;
import gloit.hiperionida.helios.model.ConciliacionBancoModel;
import gloit.hiperionida.helios.repository.ConciliacionBancoDAO;
import gloit.hiperionida.helios.service.ConciliacionBancoService;
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
public class ConciliacionBancoServiceImpl implements ConciliacionBancoService {
    private final ConciliacionBancoDAO conciliacionBancoDAO;
    private final ConciliacionBancoMapper conciliacionBancoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public ConciliacionBancoModel buscarPorId(Long id) {
        log.info("Buscando la entidad ConciliacionBanco con id: {}.", id);
        ConciliacionBancoModel conciliacionBancoModel = conciliacionBancoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad ConciliacionBanco con id: " + id + "."));
        String mensaje = "Se encontro una entidad ConciliacionBanco.";
        log.info(mensaje);
        return conciliacionBancoModel;
    }

    @Override
    public ConciliacionBancoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad ConciliacionBanco con id: {}, incluidas las eliminadas.", id);
        ConciliacionBancoModel conciliacionBancoModel = conciliacionBancoDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad ConciliacionBanco con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad ConciliacionBanco con id: " + id + ".");
        return conciliacionBancoModel;
    }

    @Override
    public List<ConciliacionBancoModel> buscarTodas() {
        log.info("Buscando todas las entidades ConciliacionBanco.");
        List<ConciliacionBancoModel> listado = conciliacionBancoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades ConciliacionBanco.");
        return listado;
    }

    @Override
    public List<ConciliacionBancoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades ConciliacionBanco, incluidas las eliminadas.");
        List<ConciliacionBancoModel> listado = conciliacionBancoDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades ConciliacionBanco, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = conciliacionBancoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades ConciliacionBanco.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = conciliacionBancoDAO.count();
        log.info("Existen {} entidades ConciliacionBanco, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ConciliacionBancoModel guardar(ConciliacionBancoCreation creation) {
        log.info("Insertando la entidad ConciliacionBanco: {}.",  creation);
        ConciliacionBancoModel conciliacionBancoModel = conciliacionBancoDAO.save(conciliacionBancoMapper.toEntity(creation));
        if (creation.getId() == null) {
            conciliacionBancoModel.setCreada(Helper.getNow(""));
            conciliacionBancoModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            conciliacionBancoModel.setModificada(Helper.getNow(""));
            conciliacionBancoModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return conciliacionBancoDAO.save(conciliacionBancoModel);
    }

    @Override
    public ConciliacionBancoModel eliminar(Long id) {
        log.info("Eliminando la entidad ConciliacionBanco con id: {}.", id);
        ConciliacionBancoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad ConciliacionBanco con id: " + id + ", fue eliminada correctamente.");
        return conciliacionBancoDAO.save(objeto);
    }

    @Override
    public ConciliacionBancoModel reciclar(Long id) {
        log.info("Reciclando la entidad ConciliacionBanco con id: {}.", id);
        ConciliacionBancoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad ConciliacionBanco con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad ConciliacionBanco con id: " + id + ", fue reciclada correctamente.");
        return conciliacionBancoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad ConciliacionBanco con id: {}.", id);
        ConciliacionBancoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad ConciliacionBanco con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        conciliacionBancoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
