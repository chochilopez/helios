package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.CategoriaViajeMapper;
import gloit.hiperionida.helios.mapper.creation.CategoriaViajeCreation;
import gloit.hiperionida.helios.model.CategoriaViajeModel;
import gloit.hiperionida.helios.repository.CategoriaViajeDAO;
import gloit.hiperionida.helios.service.CategoriaViajeService;
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
public class CategoriaViajeServiceImpl implements CategoriaViajeService {
    private final CategoriaViajeDAO categoriaViajeDAO;
    private final CategoriaViajeMapper categoriaViajeMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public CategoriaViajeModel buscarPorId(Long id) {
        log.info("Buscando la entidad CategoriaViaje con id: {}.", id);
        CategoriaViajeModel categoriaViajeModel = categoriaViajeDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad CategoriaViaje con id: " + id + "."));
        String mensaje = "Se encontro una entidad CategoriaViaje.";
        log.info(mensaje);
        return categoriaViajeModel;
    }

    @Override
    public CategoriaViajeModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad CategoriaViaje con id: {}, incluidas las eliminadas.", id);
        CategoriaViajeModel categoriaViajeModel = categoriaViajeDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad CategoriaViaje con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad CategoriaViaje con id: " + id + ".");
        return categoriaViajeModel;
    }

    @Override
    public List<CategoriaViajeModel> buscarTodas() {
        log.info("Buscando todas las entidades CategoriaViaje.");
        List<CategoriaViajeModel> listado = categoriaViajeDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades CategoriaViaje.");
        return listado;
    }

    @Override
    public List<CategoriaViajeModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades CategoriaViaje, incluidas las eliminadas.");
        List<CategoriaViajeModel> listado = categoriaViajeDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades CategoriaViaje, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = categoriaViajeDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades CategoriaViaje.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = categoriaViajeDAO.count();
        log.info("Existen {} entidades CategoriaViaje, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public CategoriaViajeModel guardar(CategoriaViajeCreation creation) {
        log.info("Insertando la entidad CategoriaViaje: {}.",  creation);
        CategoriaViajeModel categoriaViajeModel = categoriaViajeDAO.save(categoriaViajeMapper.toEntity(creation));
        if (creation.getId() == null) {
            categoriaViajeModel.setCreada(Helper.getNow(""));
            categoriaViajeModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            categoriaViajeModel.setModificada(Helper.getNow(""));
            categoriaViajeModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return categoriaViajeDAO.save(categoriaViajeModel);
    }

    @Override
    public CategoriaViajeModel eliminar(Long id) {
        log.info("Eliminando la entidad CategoriaViaje con id: {}.", id);
        CategoriaViajeModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad CategoriaViaje con id: " + id + ", fue eliminada correctamente.");
        return categoriaViajeDAO.save(objeto);
    }

    @Override
    public CategoriaViajeModel reciclar(Long id) {
        log.info("Reciclando la entidad CategoriaViaje con id: {}.", id);
        CategoriaViajeModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad CategoriaViaje con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad CategoriaViaje con id: " + id + ", fue reciclada correctamente.");
        return categoriaViajeDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad CategoriaViaje con id: {}.", id);
        CategoriaViajeModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad CategoriaViaje con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        categoriaViajeDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
