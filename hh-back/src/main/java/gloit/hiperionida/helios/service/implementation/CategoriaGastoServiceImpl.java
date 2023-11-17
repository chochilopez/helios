package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.CategoriaGastoMapper;
import gloit.hiperionida.helios.mapper.creation.CategoriaGastoCreation;
import gloit.hiperionida.helios.model.CategoriaGastoModel;
import gloit.hiperionida.helios.repository.CategoriaGastoDAO;
import gloit.hiperionida.helios.service.CategoriaGastoService;
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
public class CategoriaGastoServiceImpl implements CategoriaGastoService {
    private final CategoriaGastoDAO categoriaGastoDAO;
    private final CategoriaGastoMapper categoriaGastoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public CategoriaGastoModel buscarPorId(Long id) {
        log.info("Buscando la entidad CategoriaGasto con id: {}.", id);
        CategoriaGastoModel categoriaGastoModel = categoriaGastoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad CategoriaGasto con id: " + id + "."));
        log.info("Se encontró una entidad CategoriaGasto con id: " + id + ".");
        return categoriaGastoModel;
    }

    @Override
    public CategoriaGastoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad CategoriaGasto con id: {}, incluidas las eliminadas.", id);
        CategoriaGastoModel categoriaGastoModel = categoriaGastoDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad CategoriaGasto con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad CategoriaGasto con id: " + id + ", incluidas las eliminadas.");
        return categoriaGastoModel;
    }

    @Override
    public List<CategoriaGastoModel> buscarTodas() {
        log.info("Buscando todas las entidades CategoriaGasto.");
        List<CategoriaGastoModel> listado = categoriaGastoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CategoriaGasto.");
        return listado;
    }

    @Override
    public List<CategoriaGastoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades CategoriaGasto, incluidas las eliminadas.");
        List<CategoriaGastoModel> listado = categoriaGastoDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CategoriaGasto, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<CategoriaGastoModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades CategoriaGasto, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<CategoriaGastoModel> slice = categoriaGastoDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CategoriaGasto.");
        return slice;
    }

    @Override
    public Slice<CategoriaGastoModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades CategoriaGasto, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<CategoriaGastoModel> slice = categoriaGastoDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades CategoriaGasto, incluidas las eliminadas.");
        return slice;
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
            categoriaGastoModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            categoriaGastoModel.setModificada(Helper.getNow(""));
            categoriaGastoModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return categoriaGastoDAO.save(categoriaGastoModel);
    }

    @Override
    public CategoriaGastoModel eliminar(Long id) {
        log.info("Eliminando la entidad CategoriaGasto con id: {}.", id);
        CategoriaGastoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad CategoriaGasto con id: " + id + ", fue eliminada correctamente.");
        return categoriaGastoDAO.save(objeto);
    }

    @Override
    public CategoriaGastoModel reciclar(Long id) {
        log.info("Reciclando la entidad CategoriaGasto con id: {}.", id);
        CategoriaGastoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad CategoriaGasto con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad CategoriaGasto con id: " + id + ", fue reciclada correctamente.");
        return categoriaGastoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad CategoriaGasto con id: {}.", id);
        CategoriaGastoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad CategoriaGasto con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        categoriaGastoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
