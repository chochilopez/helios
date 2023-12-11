package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.AdelantoMapper;
import gloit.hiperionida.helios.mapper.creation.AdelantoCreation;
import gloit.hiperionida.helios.model.AdelantoModel;
import gloit.hiperionida.helios.repository.AdelantoDAO;
import gloit.hiperionida.helios.service.AdelantoService;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.exception.ObjectoNoEliminadoException;
import gloit.hiperionida.helios.util.exception.ParametroInvalidoException;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdelantoServiceImpl implements AdelantoService {
    private final AdelantoDAO adelantoDAO;
    private final AdelantoMapper adelantoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public List<AdelantoModel> buscarTodasPorCajaId(Long id) {
        log.info("Buscando todas las entidades Adelanto con id de Caja: {}.", id);
        List<AdelantoModel> listado = adelantoDAO.findAllByCajaIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Adelanto con id de Caja: " + id + ".");
        return listado;
    }

    @Override
    public List<AdelantoModel> buscarTodasPorCajaIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Adelanto con id de Caja: {}, con eliminadas.", id);
        List<AdelantoModel> listado = adelantoDAO.findAllByCajaId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Adelanto con id de Caja: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<AdelantoModel> buscarTodasPorConductorId(Long id) {
        log.info("Buscando todas las entidades Adelanto con id de Conductor: {}.", id);
        List<AdelantoModel> listado = adelantoDAO.findAllByConductorIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Adelanto con id de Conductor: " + id + ".");
        return listado;
    }

    @Override
    public List<AdelantoModel> buscarTodasPorConductorIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Adelanto con id de Conductor: {}, con eliminadas.", id);
        List<AdelantoModel> listado = adelantoDAO.findAllByConductorId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Adelanto con id de Conductor: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<AdelantoModel> buscarTodasPorFechaEntre(String inicio, String fin) {
        log.info("Buscando todas las entidades Adelanto entre las fechas: {} y {}.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son válidas.");
        List<AdelantoModel> listado = adelantoDAO.findAllByFechaBetweenAndEliminadaIsNull( fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Adelanto entre las fechas: " + inicio + " y " + fin + ".");
        return listado;
    }

    @Override
    public List<AdelantoModel> buscarTodasPorFechaEntreConEliminadas(String inicio, String fin) {
        log.info("Buscando todas las entidades Adelanto entre las fechas: {} y {}, con eliminadas.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son válidas.");
        List<AdelantoModel> listado = adelantoDAO.findAllByFechaBetween(fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades entre las fechas: " + inicio + " y " + fin + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<AdelantoModel> buscarTodasPorMontoEntre(Double min, Double max) {
        log.info("Buscando todas las entidades Adelanto con un rango de monto entre: {} y {}.", min, max);
        List<AdelantoModel> listado = adelantoDAO.findAllByMontoBetweenAndEliminadaIsNull(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Adelanto con un rango de monto cargado entre: " + min + " y " + max + ".");
        return listado;
    }

    @Override
    public List<AdelantoModel> buscarTodasPorMontoEntreConEliminadas(Double min, Double max) {
        log.info("Buscando todas las entidades Adelanto con un rango de monto entre: {} y {}, con eliminadas.", min, max);
        List<AdelantoModel> listado = adelantoDAO.findAllByMontoBetween(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Adelanto con un rango de monto cargado entre: " + min + " y " + max + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<AdelantoModel> buscarTodasPorNotas(String notas) {
        log.info("Buscando todas las entidades Adelanto con notas: {}.", notas);
        List<AdelantoModel> listado = adelantoDAO.findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Adelanto con notas: " + notas + ".");
        return listado;
    }

    @Override
    public List<AdelantoModel> buscarTodasPorNotasConEliminadas(String notas) {
        log.info("Buscando todas las entidades Adelanto con notas: {}, con eliminadas.", notas);
        List<AdelantoModel> listado = adelantoDAO.findAllByNotasContainingIgnoreCase(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Adelanto con notas: " + notas + ", con eliminadas.");
        return listado;
    }

    @Override
    public AdelantoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Adelanto con id: {}.", id);
        AdelantoModel adelantoModel = adelantoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Adelanto con id: " + id + "."));
        log.info("Se encontró una entidad Adelanto con id: " + id + ".");
        return adelantoModel;
    }

    @Override
    public AdelantoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Adelanto con id: {}, incluidas las eliminadas.", id);
        AdelantoModel adelantoModel = adelantoDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Adelanto con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Adelanto con id: " + id + ", incluidas las eliminadas.");
        return adelantoModel;
    }

    @Override
    public List<AdelantoModel> buscarTodas() {
        log.info("Buscando todas las entidades Adelanto.");
        List<AdelantoModel> listado = adelantoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Adelanto.");
        return listado;
    }

    @Override
    public List<AdelantoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Adelanto, incluidas las eliminadas.");
        List<AdelantoModel> listado = adelantoDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Adelanto, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<AdelantoModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Adelanto, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<AdelantoModel> slice = adelantoDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Adelanto.");
        return slice;
    }

    @Override
    public Slice<AdelantoModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Adelanto, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<AdelantoModel> slice = adelantoDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Adelanto, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = adelantoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Adelanto.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = adelantoDAO.count();
        log.info("Existen {} entidades Adelanto, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public AdelantoModel crear(AdelantoModel model) {
        log.info("Insertando la entidad AdelantoModel: {}.",  model);
        AdelantoModel adelantoModel = adelantoDAO.save(model);
        if (model.getId() == null) {
            adelantoModel.setCreada(Helper.getNow(""));
            adelantoModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad AdelantoModel.");
        } else {
            adelantoModel.setModificada(Helper.getNow(""));
            adelantoModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad AdelantoModel.");
        }
        return adelantoDAO.save(adelantoModel);
    }

    @Override
    public AdelantoModel guardar(AdelantoCreation creation) {
        log.info("Insertando la entidad AdelantoCreation: {}.",  creation);
        AdelantoModel adelantoModel = adelantoDAO.save(adelantoMapper.toEntity(creation));
        if (creation.getId() == null) {
            adelantoModel.setCreada(Helper.getNow(""));
            adelantoModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad AdelantoCreation.");
        } else {
            adelantoModel.setModificada(Helper.getNow(""));
            adelantoModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad AdelantoCreation.");
        }
        return adelantoDAO.save(adelantoModel);
    }

    @Override
    public AdelantoModel eliminar(Long id) {
        log.info("Eliminando la entidad Adelanto con id: {}.", id);
        AdelantoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Adelanto con id: " + id + ", fue eliminada correctamente.");
        return adelantoDAO.save(objeto);
    }

    @Override
    public AdelantoModel reciclar(Long id) {
        log.info("Reciclando la entidad Adelanto con id: {}.", id);
        AdelantoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Adelanto con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Adelanto con id: " + id + ", fue reciclada correctamente.");
        return adelantoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Adelanto con id: {}.", id);
        AdelantoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Adelanto con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        adelantoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
