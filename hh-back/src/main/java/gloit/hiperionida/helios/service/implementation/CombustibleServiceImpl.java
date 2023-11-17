package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.CombustibleMapper;
import gloit.hiperionida.helios.mapper.creation.CombustibleCreation;
import gloit.hiperionida.helios.model.CombustibleModel;
import gloit.hiperionida.helios.repository.CombustibleDAO;
import gloit.hiperionida.helios.service.CombustibleService;
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
public class CombustibleServiceImpl implements CombustibleService {
    private final CombustibleDAO combustibleDAO;
    private final CombustibleMapper combustibleMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public CombustibleModel buscarPorId(Long id) {
        log.info("Buscando la entidad Combustible con id: {}.", id);
        CombustibleModel combustibleModel = combustibleDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Combustible con id: " + id + "."));
        log.info("Se encontró una entidad Combustible con id: " + id + ".");
        return combustibleModel;
    }

    @Override
    public CombustibleModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Combustible con id: {}, incluidas las eliminadas.", id);
        CombustibleModel combustibleModel = combustibleDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Combustible con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Combustible con id: " + id + ", incluidas las eliminadas.");
        return combustibleModel;
    }

    @Override
    public List<CombustibleModel> buscarTodas() {
        log.info("Buscando todas las entidades Combustible.");
        List<CombustibleModel> listado = combustibleDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible.");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Combustible, incluidas las eliminadas.");
        List<CombustibleModel> listado = combustibleDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<CombustibleModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Combustible, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<CombustibleModel> slice = combustibleDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible.");
        return slice;
    }

    @Override
    public Slice<CombustibleModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Combustible, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<CombustibleModel> slice = combustibleDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = combustibleDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Combustible.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = combustibleDAO.count();
        log.info("Existen {} entidades Combustible, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public CombustibleModel guardar(CombustibleCreation creation) {
        log.info("Insertando la entidad Combustible: {}.",  creation);
        CombustibleModel combustibleModel = combustibleDAO.save(combustibleMapper.toEntity(creation));
        if (creation.getId() == null) {
            combustibleModel.setCreada(Helper.getNow(""));
            combustibleModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            combustibleModel.setModificada(Helper.getNow(""));
            combustibleModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return combustibleDAO.save(combustibleModel);
    }

    @Override
    public CombustibleModel eliminar(Long id) {
        log.info("Eliminando la entidad Combustible con id: {}.", id);
        CombustibleModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Combustible con id: " + id + ", fue eliminada correctamente.");
        return combustibleDAO.save(objeto);
    }

    @Override
    public CombustibleModel reciclar(Long id) {
        log.info("Reciclando la entidad Combustible con id: {}.", id);
        CombustibleModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Combustible con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Combustible con id: " + id + ", fue reciclada correctamente.");
        return combustibleDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Combustible con id: {}.", id);
        CombustibleModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Combustible con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        combustibleDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
