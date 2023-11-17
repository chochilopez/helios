package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.ConciliacionBancoMapper;
import gloit.hiperionida.helios.mapper.creation.ConciliacionBancoCreation;
import gloit.hiperionida.helios.model.ConciliacionBancoModel;
import gloit.hiperionida.helios.repository.ConciliacionBancoDAO;
import gloit.hiperionida.helios.service.ConciliacionBancoService;
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
public class ConciliacionBancoServiceImpl implements ConciliacionBancoService {
    private final ConciliacionBancoDAO conciliacionBancoDAO;
    private final ConciliacionBancoMapper conciliacionBancoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public ConciliacionBancoModel buscarPorId(Long id) {
        log.info("Buscando la entidad ConciliacionBanco con id: {}.", id);
        ConciliacionBancoModel conciliacionBancoModel = conciliacionBancoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad ConciliacionBanco con id: " + id + "."));
        log.info("Se encontró una entidad ConciliacionBanco con id: " + id + ".");
        return conciliacionBancoModel;
    }

    @Override
    public ConciliacionBancoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad ConciliacionBanco con id: {}, incluidas las eliminadas.", id);
        ConciliacionBancoModel conciliacionBancoModel = conciliacionBancoDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad ConciliacionBanco con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad ConciliacionBanco con id: " + id + ", incluidas las eliminadas.");
        return conciliacionBancoModel;
    }

    @Override
    public List<ConciliacionBancoModel> buscarTodas() {
        log.info("Buscando todas las entidades ConciliacionBanco.");
        List<ConciliacionBancoModel> listado = conciliacionBancoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades ConciliacionBanco.");
        return listado;
    }

    @Override
    public List<ConciliacionBancoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades ConciliacionBanco, incluidas las eliminadas.");
        List<ConciliacionBancoModel> listado = conciliacionBancoDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades ConciliacionBanco, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<ConciliacionBancoModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades ConciliacionBanco, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<ConciliacionBancoModel> slice = conciliacionBancoDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades ConciliacionBanco.");
        return slice;
    }

    @Override
    public Slice<ConciliacionBancoModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades ConciliacionBanco, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<ConciliacionBancoModel> slice = conciliacionBancoDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades ConciliacionBanco, incluidas las eliminadas.");
        return slice;
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
            conciliacionBancoModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            conciliacionBancoModel.setModificada(Helper.getNow(""));
            conciliacionBancoModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return conciliacionBancoDAO.save(conciliacionBancoModel);
    }

    @Override
    public ConciliacionBancoModel eliminar(Long id) {
        log.info("Eliminando la entidad ConciliacionBanco con id: {}.", id);
        ConciliacionBancoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad ConciliacionBanco con id: " + id + ", fue eliminada correctamente.");
        return conciliacionBancoDAO.save(objeto);
    }

    @Override
    public ConciliacionBancoModel reciclar(Long id) {
        log.info("Reciclando la entidad ConciliacionBanco con id: {}.", id);
        ConciliacionBancoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad ConciliacionBanco con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad ConciliacionBanco con id: " + id + ", fue reciclada correctamente.");
        return conciliacionBancoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad ConciliacionBanco con id: {}.", id);
        ConciliacionBancoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad ConciliacionBanco con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        conciliacionBancoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
