package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.ServicioMapper;
import gloit.hiperionida.helios.mapper.creation.ServicioCreation;
import gloit.hiperionida.helios.model.ServicioModel;
import gloit.hiperionida.helios.repository.ServicioDAO;
import gloit.hiperionida.helios.service.ServicioService;
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
public class ServicioServiceImpl implements ServicioService {
    private final ServicioDAO servicioDAO;
    private final ServicioMapper servicioMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public ServicioModel buscarPorId(Long id) {
        log.info("Buscando la entidad Servicio con id: {}.", id);
        ServicioModel servicioModel = servicioDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Servicio con id: " + id + "."));
        log.info("Se encontro una entidad Servicio con id: " + id + ".");
        return servicioModel;
    }

    @Override
    public ServicioModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Servicio con id: {}, incluidas las eliminadas.", id);
        ServicioModel servicioModel = servicioDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Servicio con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Servicio con id: " + id + ", incluidas las eliminadas.");
        return servicioModel;
    }

    @Override
    public List<ServicioModel> buscarTodas() {
        log.info("Buscando todas las entidades Servicio.");
        List<ServicioModel> listado = servicioDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Servicio.");
        return listado;
    }

    @Override
    public List<ServicioModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Servicio, incluidas las eliminadas.");
        List<ServicioModel> listado = servicioDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Servicio, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<ServicioModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Servicio, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<ServicioModel> slice = servicioDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Servicio.");
        return slice;
    }

    @Override
    public Slice<ServicioModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Servicio, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<ServicioModel> slice = servicioDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Servicio, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = servicioDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Servicio.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = servicioDAO.count();
        log.info("Existen {} entidades Servicio, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ServicioModel guardar(ServicioCreation creation) {
        log.info("Insertando la entidad Servicio: {}.",  creation);
        ServicioModel servicioModel = servicioDAO.save(servicioMapper.toEntity(creation));
        if (creation.getId() == null) {
            servicioModel.setCreada(Helper.getNow(""));
            servicioModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            servicioModel.setModificada(Helper.getNow(""));
            servicioModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return servicioDAO.save(servicioModel);
    }

    @Override
    public ServicioModel eliminar(Long id) {
        log.info("Eliminando la entidad Servicio con id: {}.", id);
        ServicioModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Servicio con id: " + id + ", fue eliminada correctamente.");
        return servicioDAO.save(objeto);
    }

    @Override
    public ServicioModel reciclar(Long id) {
        log.info("Reciclando la entidad Servicio con id: {}.", id);
        ServicioModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Servicio con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Servicio con id: " + id + ", fue reciclada correctamente.");
        return servicioDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Servicio con id: {}.", id);
        ServicioModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Servicio con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        servicioDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
