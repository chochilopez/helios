package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.DireccionMapper;
import gloit.hiperionida.helios.mapper.creation.DireccionCreation;
import gloit.hiperionida.helios.model.CiudadModel;
import gloit.hiperionida.helios.model.DireccionModel;
import gloit.hiperionida.helios.repository.CiudadDAO;
import gloit.hiperionida.helios.repository.DireccionDAO;
import gloit.hiperionida.helios.service.DireccionService;
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
public class DireccionServiceImpl implements DireccionService {
    private final CiudadDAO ciudadDAO;
    private final DireccionDAO direccionDAO;
    private final DireccionMapper direccionMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public List<CiudadModel> buscarTodasCiudad() {
        log.info("Buscando todas las entidades Ciudad.");
        List<CiudadModel> listado = ciudadDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Ciudad.");
        return listado;
    }

    @Override
    public List<DireccionModel> buscarTodasPorCiudad(String ciudad) {
        log.info("Buscando todas las entidades Direccion con ciudad: {}.", ciudad);
        List<DireccionModel> listado = direccionDAO.findAllByCiudadContainingIgnoreCaseAndEliminadaIsNull(ciudad);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Direccion con ciudad: " + ciudad + ".");
        return listado;
    }

    @Override
    public List<DireccionModel> buscarTodasPorCiudadConEliminadas(String ciudad) {
        log.info("Buscando todas las entidades Direccion con ciudad: {}, incluidas las eliminadas.", ciudad);
        List<DireccionModel> listado = direccionDAO.findAllByCiudadContainingIgnoreCase(ciudad);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Direccion con ciudad: " + ciudad + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<DireccionModel> buscarTodasPorDireccion(String direccion) {
        log.info("Buscando todas las entidades Direccion con direccion: {}.", direccion);
        List<DireccionModel> listado = direccionDAO.findAllByDireccionContainingIgnoreCaseAndEliminadaIsNull(direccion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Direccion con direccion: " + direccion + ".");
        return listado;
    }

    @Override
    public List<DireccionModel> buscarTodasPorDireccionConEliminadas(String direccion) {
        log.info("Buscando todas las entidades Direccion con direccion: {}, incluidas las eliminadas.", direccion);
        List<DireccionModel> listado = direccionDAO.findAllByDireccionContainingIgnoreCase(direccion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Direccion con direccion: " + direccion + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<DireccionModel> buscarTodasPorNombre(String nombre) {
        log.info("Buscando todas las entidades Direccion con nombre: {}.", nombre);
        List<DireccionModel> listado = direccionDAO.findAllByNombreContainingIgnoreCaseAndEliminadaIsNull(nombre);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Direccion con nombre: " + nombre + ".");
        return listado;
    }

    @Override
    public List<DireccionModel> buscarTodasPorNombreConEliminadas(String nombre) {
        log.info("Buscando todas las entidades Direccion con nombre: {}, incluidas las eliminadas.", nombre);
        List<DireccionModel> listado = direccionDAO.findAllByNombreContainingIgnoreCase(nombre);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Direccion con nombre: " + nombre + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<DireccionModel> buscarTodasPorNotas(String notas) {
        log.info("Buscando todas las entidades Direccion con notas: {}.", notas);
        List<DireccionModel> listado = direccionDAO.findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Direccion con notas: " + notas + ".");
        return listado;
    }

    @Override
    public List<DireccionModel> buscarTodasPorNotasConEliminadas(String notas) {
        log.info("Buscando todas las entidades Direccion con notas: {}, incluidas las eliminadas.", notas);
        List<DireccionModel> listado = direccionDAO.findAllByNotasContainingIgnoreCase(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Direccion con notas: " + notas + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public DireccionModel buscarPorId(Long id) {
        log.info("Buscando la entidad Direccion con id: {}.", id);
        DireccionModel direccionModel = direccionDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Direccion con id: " + id + "."));
        log.info("Se encontró una entidad Direccion con id: " + id + ".");
        return direccionModel;
    }

    @Override
    public DireccionModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Direccion con id: {}, incluidas las eliminadas.", id);
        DireccionModel direccionModel = direccionDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Direccion con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Direccion con id: " + id + ", incluidas las eliminadas.");
        return direccionModel;
    }

    @Override
    public List<DireccionModel> buscarTodas() {
        log.info("Buscando todas las entidades Direccion.");
        List<DireccionModel> listado = direccionDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Direccion.");
        return listado;
    }

    @Override
    public List<DireccionModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Direccion, incluidas las eliminadas.");
        List<DireccionModel> listado = direccionDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Direccion, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<DireccionModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Direccion, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<DireccionModel> slice = direccionDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Direccion.");
        return slice;
    }

    @Override
    public Slice<DireccionModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Direccion, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<DireccionModel> slice = direccionDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Direccion, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = direccionDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Direccion.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = direccionDAO.count();
        log.info("Existen {} entidades Direccion, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public DireccionModel guardar(DireccionCreation creation) {
        log.info("Insertando la entidad Direccion: {}.",  creation);
        DireccionModel direccionModel = direccionDAO.save(direccionMapper.toEntity(creation));
        if (creation.getId() == null) {
            direccionModel.setCreada(Helper.getNow(""));
            direccionModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            direccionModel.setModificada(Helper.getNow(""));
            direccionModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return direccionDAO.save(direccionModel);
    }

    @Override
    public DireccionModel eliminar(Long id) {
        log.info("Eliminando la entidad Direccion con id: {}.", id);
        DireccionModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Direccion con id: " + id + ", fue eliminada correctamente.");
        return direccionDAO.save(objeto);
    }

    @Override
    public DireccionModel reciclar(Long id) {
        log.info("Reciclando la entidad Direccion con id: {}.", id);
        DireccionModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Direccion con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Direccion con id: " + id + ", fue reciclada correctamente.");
        return direccionDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Direccion con id: {}.", id);
        DireccionModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Direccion con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        direccionDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
