package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.ClienteMapper;
import gloit.hiperionida.helios.mapper.creation.ClienteCreation;
import gloit.hiperionida.helios.model.ClienteModel;
import gloit.hiperionida.helios.repository.ClienteDAO;
import gloit.hiperionida.helios.service.ClienteService;
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
public class ClienteServiceImpl implements ClienteService {
    private final ClienteDAO clienteDAO;
    private final ClienteMapper clienteMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public List<ClienteModel> buscarTodasPorDireccion(String direccion) {
        log.info("Buscando todas las entidad Cliente con direccion: {}.", direccion);
        List<ClienteModel> listado = clienteDAO.findAllByDireccionContainingIgnoreCaseAndEliminadaIsNull(direccion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente.");
        return listado;
    }

    @Override
    public List<ClienteModel> buscarTodasPorDireccionConEliminadas(String direccion) {
        log.info("Buscando todas las entidad Cliente con direccion: {}, incluidas las eliminadas.", direccion);
        List<ClienteModel> listado = clienteDAO.findAllByDireccionContainingIgnoreCaseAndEliminadaIsNull(direccion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<ClienteModel> buscarTodasPorEmail(String email) {
        log.info("Buscando todas las entidad Cliente con email: {}.", email);
        List<ClienteModel> listado = clienteDAO.findAllByEmailContainingIgnoreCaseAndEliminadaIsNull(email);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente.");
        return listado;
    }

    @Override
    public List<ClienteModel> buscarTodasPorEmailConEliminadas(String email) {
        log.info("Buscando todas las entidad Cliente con email: {}, incluidas las eliminadas.", email);
        List<ClienteModel> listado = clienteDAO.findAllByEmailContainingIgnoreCaseAndEliminadaIsNull(email);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<ClienteModel> buscarTodasPorIdentificacion(String identificacion) {
        log.info("Buscando todas las entidad Cliente con identificacion: {}.", identificacion);
        List<ClienteModel> listado = clienteDAO.findAllByIdentificacionContainingIgnoreCaseAndEliminadaIsNull(identificacion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente.");
        return listado;
    }

    @Override
    public List<ClienteModel> buscarTodasPorIdentificacionConEliminadas(String identificacion) {
        log.info("Buscando todas las entidad Cliente con identificacion: {}, incluidas las eliminadas.", identificacion);
        List<ClienteModel> listado = clienteDAO.findAllByIdentificacionContainingIgnoreCaseAndEliminadaIsNull(identificacion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<ClienteModel> buscarTodasPorNombre(String nombre) {
        log.info("Buscando todas las entidad Cliente con nombre: {}.", nombre);
        List<ClienteModel> listado = clienteDAO.findAllByNombreContainingIgnoreCaseAndEliminadaIsNull(nombre);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente.");
        return listado;
    }

    @Override
    public List<ClienteModel> buscarTodasPorNombreConEliminadas(String nombre) {
        log.info("Buscando todas las entidad Cliente con nombre: {}, incluidas las eliminadas.", nombre);
        List<ClienteModel> listado = clienteDAO.findAllByNombreContainingIgnoreCaseAndEliminadaIsNull(nombre);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<ClienteModel> buscarTodasPorNotas(String notas) {
        log.info("Buscando todas las entidad Cliente con notas: {}.", notas);
        List<ClienteModel> listado = clienteDAO.findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente.");
        return listado;
    }

    @Override
    public List<ClienteModel> buscarTodasPorNotasConEliminadas(String notas) {
        log.info("Buscando todas las entidad Cliente con notas: {}, incluidas las eliminadas.", notas);
        List<ClienteModel> listado = clienteDAO.findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<ClienteModel> buscarTodasPorTelefono(String telefono) {
        log.info("Buscando todas las entidad Cliente con telefono: {}.", telefono);
        List<ClienteModel> listado = clienteDAO.findAllByTelefonoContainingIgnoreCaseAndEliminadaIsNull(telefono);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente.");
        return listado;
    }

    @Override
    public List<ClienteModel> buscarTodasPorTelefonoConEliminadas(String telefono) {
        log.info("Buscando todas las entidad Cliente con telefono: {}, incluidas las eliminadas.", telefono);
        List<ClienteModel> listado = clienteDAO.findAllByTelefonoContainingIgnoreCaseAndEliminadaIsNull(telefono);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public ClienteModel buscarPorId(Long id) {
        log.info("Buscando la entidad Cliente con id: {}.", id);
        ClienteModel clienteModel = clienteDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Cliente con id: " + id + "."));
        String mensaje = "Se encontro una entidad Cliente.";
        log.info(mensaje);
        return clienteModel;
    }

    @Override
    public ClienteModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Cliente con id: {}, incluidas las eliminadas.", id);
        ClienteModel clienteModel = clienteDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Cliente con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Cliente con id: " + id + ".");
        return clienteModel;
    }

    @Override
    public List<ClienteModel> buscarTodas() {
        log.info("Buscando todas las entidades Cliente.");
        List<ClienteModel> listado = clienteDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente.");
        return listado;
    }

    @Override
    public List<ClienteModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Cliente, incluidas las eliminadas.");
        List<ClienteModel> listado = clienteDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<ClienteModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Cliente, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<ClienteModel> slice = clienteDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente.");
        return slice;
    }

    @Override
    public Slice<ClienteModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Cliente, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<ClienteModel> slice = clienteDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cliente, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = clienteDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Cliente.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = clienteDAO.count();
        log.info("Existen {} entidades Cliente, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ClienteModel guardar(ClienteCreation creation) {
        log.info("Insertando la entidad Cliente: {}.",  creation);
        ClienteModel clienteModel = clienteDAO.save(clienteMapper.toEntity(creation));
        if (creation.getId() == null) {
            clienteModel.setCreada(Helper.getNow(""));
            clienteModel.setCreador_id(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            clienteModel.setModificada(Helper.getNow(""));
            clienteModel.setModificador_id(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return clienteDAO.save(clienteModel);
    }

    @Override
    public ClienteModel eliminar(Long id) {
        log.info("Eliminando la entidad Cliente con id: {}.", id);
        ClienteModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador_id(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Cliente con id: " + id + ", fue eliminada correctamente.");
        return clienteDAO.save(objeto);
    }

    @Override
    public ClienteModel reciclar(Long id) {
        log.info("Reciclando la entidad Cliente con id: {}.", id);
        ClienteModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Cliente con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador_id(null);
        log.info("La entidad Cliente con id: " + id + ", fue reciclada correctamente.");
        return clienteDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Cliente con id: {}.", id);
        ClienteModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Cliente con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        clienteDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
