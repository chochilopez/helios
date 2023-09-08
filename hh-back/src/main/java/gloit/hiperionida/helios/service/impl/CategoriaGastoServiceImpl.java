package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.CategoriaGastoMapper;
import gloit.hiperionida.helios.mapper.creation.CategoriaGastoCreation;
import gloit.hiperionida.helios.model.CategoriaGastoModel;
import gloit.hiperionida.helios.repository.CategoriaGastoDAO;
import gloit.hiperionida.helios.service.CategoriaGastoService;
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
public class CategoriaGastoServiceImpl implements CategoriaGastoService {
    private final CategoriaGastoDAO categoriaGastoDAO;
    private final CategoriaGastoMapper categoriaGastoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public CategoriaGastoModel buscarPorId(Long id) {
        log.info("Buscando la entidad CategoriaGasto con id: {}.", id);
        CategoriaGastoModel categoriaGastoModel = categoriaGastoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad CategoriaGasto con id: " + id + "."));
        String mensaje = "Se encontro una entidad CategoriaGasto.";
        log.info(mensaje);
        return categoriaGastoModel;
    }

    @Override
    public CategoriaGastoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad CategoriaGasto con id: {}, incluidas las eliminadas.", id);
        CategoriaGastoModel categoriaGastoModel = categoriaGastoDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad CategoriaGasto con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad CategoriaGasto con id: " + id + ".");
        return categoriaGastoModel;
    }

    @Override
    public List<CategoriaGastoModel> buscarTodas() {
        log.info("Buscando todas las entidades CategoriaGasto.");
        List<CategoriaGastoModel> listado = categoriaGastoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades CategoriaGasto.");
        return listado;
    }

    @Override
    public List<CategoriaGastoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades CategoriaGasto, incluidas las eliminadas.");
        List<CategoriaGastoModel> listado = categoriaGastoDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades CategoriaGasto, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = categoriaGastoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades CategoriaGasto.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = categoriaGastoDAO.count();
        log.info("Existen {} entidades CategoriaGasto, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public CategoriaGastoModel guardar(CategoriaGastoCreation creation) {
        log.info("Insertando la entidad CategoriaGasto: {}.",  creation);
        CategoriaGastoModel categoriaGastoModel = categoriaGastoDAO.save(categoriaGastoMapper.toEntity(creation));
        if (creation.getId() == null) {
            categoriaGastoModel.setCreada(Helper.getNow(""));
            categoriaGastoModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            categoriaGastoModel.setModificada(Helper.getNow(""));
            categoriaGastoModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return categoriaGastoDAO.save(categoriaGastoModel);
    }

    @Override
    public CategoriaGastoModel eliminar(Long id) {
        log.info("Eliminando la entidad CategoriaGasto con id: {}.", id);
        CategoriaGastoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad CategoriaGasto con id: " + id + ", fue eliminada correctamente.");
        return categoriaGastoDAO.save(objeto);
    }

    @Override
    public CategoriaGastoModel reciclar(Long id) {
        log.info("Reciclando la entidad CategoriaGasto con id: {}.", id);
        CategoriaGastoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad CategoriaGasto con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad CategoriaGasto con id: " + id + ", fue reciclada correctamente.");
        return categoriaGastoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad CategoriaGasto con id: {}.", id);
        CategoriaGastoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad CategoriaGasto con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        categoriaGastoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
