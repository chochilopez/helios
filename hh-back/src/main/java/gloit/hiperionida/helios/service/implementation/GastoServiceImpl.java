package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.GastoMapper;
import gloit.hiperionida.helios.mapper.creation.GastoCreation;
import gloit.hiperionida.helios.model.GastoModel;
import gloit.hiperionida.helios.repository.GastoDAO;
import gloit.hiperionida.helios.service.GastoService;
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
public class GastoServiceImpl implements GastoService {
    private final GastoDAO gastoDAO;
    private final GastoMapper gastoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public GastoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Gasto con id: {}.", id);
        GastoModel gastoModel = gastoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Gasto con id: " + id + "."));
        log.info("Se encontró una entidad Gasto con id: " + id + ".");
        return gastoModel;
    }

    @Override
    public GastoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Gasto con id: {}, incluidas las eliminadas.", id);
        GastoModel gastoModel = gastoDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Gasto con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Gasto con id: " + id + ", incluidas las eliminadas.");
        return gastoModel;
    }

    @Override
    public List<GastoModel> buscarTodas() {
        log.info("Buscando todas las entidades Gasto.");
        List<GastoModel> listado = gastoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Gasto.");
        return listado;
    }

    @Override
    public List<GastoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Gasto, incluidas las eliminadas.");
        List<GastoModel> listado = gastoDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Gasto, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<GastoModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Gasto, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<GastoModel> slice = gastoDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Gasto.");
        return slice;
    }

    @Override
    public Slice<GastoModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Gasto, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<GastoModel> slice = gastoDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Gasto, incluidas las eliminadas.");
        return slice;
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
            gastoModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            gastoModel.setModificada(Helper.getNow(""));
            gastoModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return gastoDAO.save(gastoModel);
    }

    @Override
    public GastoModel eliminar(Long id) {
        log.info("Eliminando la entidad Gasto con id: {}.", id);
        GastoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Gasto con id: " + id + ", fue eliminada correctamente.");
        return gastoDAO.save(objeto);
    }

    @Override
    public GastoModel reciclar(Long id) {
        log.info("Reciclando la entidad Gasto con id: {}.", id);
        GastoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Gasto con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Gasto con id: " + id + ", fue reciclada correctamente.");
        return gastoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Gasto con id: {}.", id);
        GastoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Gasto con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        gastoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
