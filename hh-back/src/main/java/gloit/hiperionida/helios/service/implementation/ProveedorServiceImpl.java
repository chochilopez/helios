package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.ProveedorMapper;
import gloit.hiperionida.helios.mapper.creation.ProveedorCreation;
import gloit.hiperionida.helios.mapper.creation.ProveedorCreation;
import gloit.hiperionida.helios.model.ProveedorModel;
import gloit.hiperionida.helios.model.ProveedorModel;
import gloit.hiperionida.helios.model.ProveedorModel;
import gloit.hiperionida.helios.repository.ProveedorDAO;
import gloit.hiperionida.helios.service.ProveedorService;
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
public class ProveedorServiceImpl implements ProveedorService {
    private final ProveedorDAO proveedorDAO;
    private final ProveedorMapper proveedorMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public List<ProveedorModel> buscarTodasPorDireccion(String direccion) {
        log.info("Buscando todas las entidades Proveedor con direccion: {}.", direccion);
        List<ProveedorModel> listado = proveedorDAO.findAllByDireccionContainingIgnoreCaseAndEliminadaIsNull(direccion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor con direccion: " + direccion + ".");
        return listado;
    }

    @Override
    public List<ProveedorModel> buscarTodasPorDireccionConEliminadas(String direccion) {
        log.info("Buscando todas las entidades Proveedor con direccion: {}, incluidas las eliminadas.", direccion);
        List<ProveedorModel> listado = proveedorDAO.findAllByDireccionContainingIgnoreCaseAndEliminadaIsNull(direccion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor con direccion: " + direccion + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<ProveedorModel> buscarTodasPorEmail(String email) {
        log.info("Buscando todas las entidades Proveedor con email: {}.", email);
        List<ProveedorModel> listado = proveedorDAO.findAllByEmailContainingIgnoreCaseAndEliminadaIsNull(email);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor con email: " + email + ".");
        return listado;
    }

    @Override
    public List<ProveedorModel> buscarTodasPorEmailConEliminadas(String email) {
        log.info("Buscando todas las entidades Proveedor con email: {}, incluidas las eliminadas.", email);
        List<ProveedorModel> listado = proveedorDAO.findAllByEmailContainingIgnoreCaseAndEliminadaIsNull(email);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor con email: " + email + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<ProveedorModel> buscarTodasPorIdentificacion(String identificacion) {
        log.info("Buscando todas las entidades Proveedor con identificacion: {}.", identificacion);
        List<ProveedorModel> listado = proveedorDAO.findAllByIdentificacionContainingIgnoreCaseAndEliminadaIsNull(identificacion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor con identificacion: " + identificacion + ".");
        return listado;
    }

    @Override
    public List<ProveedorModel> buscarTodasPorIdentificacionConEliminadas(String identificacion) {
        log.info("Buscando todas las entidades Proveedor con identificacion: {}, incluidas las eliminadas.", identificacion);
        List<ProveedorModel> listado = proveedorDAO.findAllByIdentificacionContainingIgnoreCaseAndEliminadaIsNull(identificacion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor con identificacion: " + identificacion + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<ProveedorModel> buscarTodasPorNombre(String nombre) {
        log.info("Buscando todas las entidades Proveedor con nombre: {}.", nombre);
        List<ProveedorModel> listado = proveedorDAO.findAllByNombreContainingIgnoreCaseAndEliminadaIsNull(nombre);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor con nombre: " + nombre + ".");
        return listado;
    }

    @Override
    public List<ProveedorModel> buscarTodasPorNombreConEliminadas(String nombre) {
        log.info("Buscando todas las entidades Proveedor con nombre: {}, incluidas las eliminadas.", nombre);
        List<ProveedorModel> listado = proveedorDAO.findAllByNombreContainingIgnoreCaseAndEliminadaIsNull(nombre);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor con nombre: " + nombre + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<ProveedorModel> buscarTodasPorNotas(String notas) {
        log.info("Buscando todas las entidades Proveedor con notas: {}.", notas);
        List<ProveedorModel> listado = proveedorDAO.findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor con notas: " + notas + ".");
        return listado;
    }

    @Override
    public List<ProveedorModel> buscarTodasPorNotasConEliminadas(String notas) {
        log.info("Buscando todas las entidades Proveedor con notas: {}, incluidas las eliminadas.", notas);
        List<ProveedorModel> listado = proveedorDAO.findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor con notas: " + notas + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<ProveedorModel> buscarTodasPorTelefono(String telefono) {
        log.info("Buscando todas las entidades Proveedor con telefono: {}.", telefono);
        List<ProveedorModel> listado = proveedorDAO.findAllByTelefonoContainingIgnoreCaseAndEliminadaIsNull(telefono);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor con telefono: " + telefono + ".");
        return listado;
    }

    @Override
    public List<ProveedorModel> buscarTodasPorTelefonoConEliminadas(String telefono) {
        log.info("Buscando todas las entidades Proveedor con telefono: {}, incluidas las eliminadas.", telefono);
        List<ProveedorModel> listado = proveedorDAO.findAllByTelefonoContainingIgnoreCaseAndEliminadaIsNull(telefono);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor con telefono: " + telefono + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public ProveedorModel buscarPorId(Long id) {
        log.info("Buscando la entidad Proveedor con id: {}.", id);
        ProveedorModel proveedorModel = proveedorDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Proveedor con id: " + id + "."));
        log.info("Se encontró una entidad Proveedor con id: " + id + ".");
        return proveedorModel;
    }

    @Override
    public ProveedorModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Proveedor con id: {}, incluidas las eliminadas.", id);
        ProveedorModel proveedorModel = proveedorDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Proveedor con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Proveedor con id: " + id + ", incluidas las eliminadas.");
        return proveedorModel;
    }

    @Override
    public List<ProveedorModel> buscarTodas() {
        log.info("Buscando todas las entidades Proveedor.");
        List<ProveedorModel> listado = proveedorDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor.");
        return listado;
    }

    @Override
    public List<ProveedorModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Proveedor, incluidas las eliminadas.");
        List<ProveedorModel> listado = proveedorDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<ProveedorModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Proveedor, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<ProveedorModel> slice = proveedorDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor.");
        return slice;
    }

    @Override
    public Slice<ProveedorModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Proveedor, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<ProveedorModel> slice = proveedorDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Proveedor, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = proveedorDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Proveedor.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = proveedorDAO.count();
        log.info("Existen {} entidades Proveedor, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ProveedorModel crear(ProveedorModel model) {
        log.info("Insertando la entidad ProveedorModel: {}.",  model);
        ProveedorModel proveedorModel = proveedorDAO.save(model);
        if (model.getId() == null) {
            proveedorModel.setCreada(Helper.getNow(""));
            proveedorModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad ProveedorModel.");
        } else {
            proveedorModel.setModificada(Helper.getNow(""));
            proveedorModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad ProveedorModel.");
        }
        return proveedorDAO.save(proveedorModel);
    }

    @Override
    public ProveedorModel guardar(ProveedorCreation creation) {
        log.info("Insertando la entidad ProveedorCreation: {}.",  creation);
        ProveedorModel proveedorModel = proveedorDAO.save(proveedorMapper.toEntity(creation));
        if (creation.getId() == null) {
            proveedorModel.setCreada(Helper.getNow(""));
            proveedorModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad ProveedorCreation.");
        } else {
            proveedorModel.setModificada(Helper.getNow(""));
            proveedorModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad ProveedorCreation.");
        }
        return proveedorDAO.save(proveedorModel);
    }

    @Override
    public ProveedorModel eliminar(Long id) {
        log.info("Eliminando la entidad Proveedor con id: {}.", id);
        ProveedorModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Proveedor con id: " + id + ", fue eliminada correctamente.");
        return proveedorDAO.save(objeto);
    }

    @Override
    public ProveedorModel reciclar(Long id) {
        log.info("Reciclando la entidad Proveedor con id: {}.", id);
        ProveedorModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Proveedor con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Proveedor con id: " + id + ", fue reciclada correctamente.");
        return proveedorDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Proveedor con id: {}.", id);
        ProveedorModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Proveedor con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        proveedorDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
