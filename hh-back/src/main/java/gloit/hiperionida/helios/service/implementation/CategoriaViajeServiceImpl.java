package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.CategoriaViajeMapper;
import gloit.hiperionida.helios.mapper.creation.CategoriaViajeCreation;
import gloit.hiperionida.helios.model.CategoriaViajeModel;
import gloit.hiperionida.helios.repository.CategoriaViajeDAO;
import gloit.hiperionida.helios.service.CategoriaViajeService;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.exception.ObjectoNoEliminadoException;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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
        CategoriaViajeModel categoriaViajeModel = categoriaViajeDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad CategoriaViaje con id: " + id + "."));
        log.info("Se encontro una entidad CategoriaViaje con id: " + id + ".");
        return categoriaViajeModel;
    }

    @Override
    public CategoriaViajeModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad CategoriaViaje con id: {}, incluidas las eliminadas.", id);
        CategoriaViajeModel categoriaViajeModel = categoriaViajeDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad CategoriaViaje con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad CategoriaViaje con id: " + id + ", incluidas las eliminadas.");
        return categoriaViajeModel;
    }

    @Override
    public List<CategoriaViajeModel> buscarTodas() {
        log.info("Buscando todas las entidades CategoriaViaje.");
        List<CategoriaViajeModel> listado = categoriaViajeDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CategoriaViaje.");
        return listado;
    }

    @Override
    public List<CategoriaViajeModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades CategoriaViaje, incluidas las eliminadas.");
        List<CategoriaViajeModel> listado = categoriaViajeDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CategoriaViaje, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<CategoriaViajeModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades CategoriaViajeModel, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<CategoriaViajeModel> slice = categoriaViajeDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CategoriaViajeModel.");
        return slice;
    }

    @Override
    public Slice<CategoriaViajeModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades CategoriaViajeModel, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<CategoriaViajeModel> slice = categoriaViajeDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CategoriaViajeModel, incluidas las eliminadas.");
        return slice;
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
            categoriaViajeModel.setCreador_id(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            categoriaViajeModel.setModificada(Helper.getNow(""));
            categoriaViajeModel.setModificador_id(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return categoriaViajeDAO.save(categoriaViajeModel);
    }

    @Override
    public CategoriaViajeModel eliminar(Long id) {
        log.info("Eliminando la entidad CategoriaViaje con id: {}.", id);
        CategoriaViajeModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador_id(usuarioService.obtenerUsuario().getId());
        log.info("La entidad CategoriaViaje con id: " + id + ", fue eliminada correctamente.");
        return categoriaViajeDAO.save(objeto);
    }

    @Override
    public CategoriaViajeModel reciclar(Long id) {
        log.info("Reciclando la entidad CategoriaViaje con id: {}.", id);
        CategoriaViajeModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad CategoriaViaje con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador_id(null);
        log.info("La entidad CategoriaViaje con id: " + id + ", fue reciclada correctamente.");
        return categoriaViajeDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad CategoriaViaje con id: {}.", id);
        CategoriaViajeModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad CategoriaViaje con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        categoriaViajeDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
