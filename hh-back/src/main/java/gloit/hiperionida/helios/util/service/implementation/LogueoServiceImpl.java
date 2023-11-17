package gloit.hiperionida.helios.util.service.implementation;

import gloit.hiperionida.helios.util.mapper.LogueoMapper;
import gloit.hiperionida.helios.util.mapper.creation.LogueoCreation;
import gloit.hiperionida.helios.util.model.LogueoModel;
import gloit.hiperionida.helios.util.repository.LogueoDAO;
import gloit.hiperionida.helios.util.service.LogueoService;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.exception.ObjectoNoEliminadoException;
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
public class LogueoServiceImpl implements LogueoService {
    private final LogueoDAO logueoDAO;
    private final LogueoMapper logueoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public LogueoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Visita con id: {}.", id);
        LogueoModel logueoModel = logueoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Visita con id: " + id + "."));
        String mensaje = "Se encontró una entidad Visita.";
        log.info(mensaje);
        return logueoModel;
    }

    @Override
    public LogueoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Visita con id: {}, incluidas las eliminadas.", id);
        LogueoModel logueoModel = logueoDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Visita con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Visita con id: " + id + ".");
        return logueoModel;
    }

    @Override
    public List<LogueoModel> buscarTodas() {
        log.info("Buscando todas las entidades Visita.");
        List<LogueoModel> listado = logueoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Visita.");
        return listado;
    }

    @Override
    public List<LogueoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Visita, incluidas las eliminadas.");
        List<LogueoModel> listado = logueoDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Visita, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<LogueoModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Visita, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<LogueoModel> slice = logueoDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Visita.");
        return slice;
    }

    @Override
    public Slice<LogueoModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Visita, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<LogueoModel> slice = logueoDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Visita, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = logueoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Visita.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = logueoDAO.count();
        log.info("Existen {} entidades Visita, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public LogueoModel guardar(LogueoCreation creation) {
        log.info("Insertando la entidad Visita: {}.",  creation);
        LogueoModel logueoModel = logueoDAO.save(logueoMapper.toEntity(creation));
        if (creation.getId() == null) {
            logueoModel.setCreada(Helper.getNow(""));
            logueoModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            logueoModel.setModificada(Helper.getNow(""));
            logueoModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return logueoDAO.save(logueoModel);
    }

    @Override
    public LogueoModel eliminar(Long id) {
        log.info("Eliminando la entidad Visita con id: {}.", id);
        LogueoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Visita con id: " + id + ", fue eliminada correctamente.");
        return logueoDAO.save(objeto);
    }

    @Override
    public LogueoModel reciclar(Long id) {
        log.info("Reciclando la entidad Visita con id: {}.", id);
        LogueoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Visita con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Visita con id: " + id + ", fue reciclada correctamente.");
        return logueoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Visita con id: {}.", id);
        LogueoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Visita con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        logueoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
