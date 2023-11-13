package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.ConductorMapper;
import gloit.hiperionida.helios.mapper.creation.ConductorCreation;
import gloit.hiperionida.helios.model.ConductorModel;
import gloit.hiperionida.helios.repository.ConductorDAO;
import gloit.hiperionida.helios.service.ConductorService;
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
public class ConductorServiceImpl implements ConductorService {
    private final ConductorDAO conductorDAO;
    private final ConductorMapper conductorMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public ConductorModel buscarPorId(Long id) {
        log.info("Buscando la entidad Conductor con id: {}.", id);
        ConductorModel conductorModel = conductorDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Conductor con id: " + id + "."));
        log.info("Se encontro una entidad Conductor con id: " + id + ".");
        return conductorModel;
    }

    @Override
    public ConductorModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Conductor con id: {}, incluidas las eliminadas.", id);
        ConductorModel conductorModel = conductorDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Conductor con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Conductor con id: " + id + ", incluidas las eliminadas.");
        return conductorModel;
    }

    @Override
    public List<ConductorModel> buscarTodas() {
        log.info("Buscando todas las entidades Conductor.");
        List<ConductorModel> listado = conductorDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Conductor.");
        return listado;
    }

    @Override
    public List<ConductorModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Conductor, incluidas las eliminadas.");
        List<ConductorModel> listado = conductorDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Conductor, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<ConductorModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Conductor, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<ConductorModel> slice = conductorDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Conductor.");
        return slice;
    }

    @Override
    public Slice<ConductorModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Conductor, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<ConductorModel> slice = conductorDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Conductor, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = conductorDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Conductor.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = conductorDAO.count();
        log.info("Existen {} entidades Conductor, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ConductorModel guardar(ConductorCreation creation) {
        log.info("Insertando la entidad Conductor: {}.",  creation);
        ConductorModel conductorModel = conductorDAO.save(conductorMapper.toEntity(creation));
        if (creation.getId() == null) {
            conductorModel.setCreada(Helper.getNow(""));
            conductorModel.setCreador_id(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            conductorModel.setModificada(Helper.getNow(""));
            conductorModel.setModificador_id(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return conductorDAO.save(conductorModel);
    }

    @Override
    public ConductorModel eliminar(Long id) {
        log.info("Eliminando la entidad Conductor con id: {}.", id);
        ConductorModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador_id(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Conductor con id: " + id + ", fue eliminada correctamente.");
        return conductorDAO.save(objeto);
    }

    @Override
    public ConductorModel reciclar(Long id) {
        log.info("Reciclando la entidad Conductor con id: {}.", id);
        ConductorModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Conductor con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador_id(null);
        log.info("La entidad Conductor con id: " + id + ", fue reciclada correctamente.");
        return conductorDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Conductor con id: {}.", id);
        ConductorModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Conductor con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        conductorDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
