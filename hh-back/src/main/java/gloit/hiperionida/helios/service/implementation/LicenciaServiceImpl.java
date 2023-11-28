package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.LicenciaMapper;
import gloit.hiperionida.helios.mapper.creation.LicenciaCreation;
import gloit.hiperionida.helios.model.LicenciaModel;
import gloit.hiperionida.helios.repository.LicenciaDAO;
import gloit.hiperionida.helios.service.LicenciaService;
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
public class LicenciaServiceImpl implements LicenciaService {
    private final LicenciaDAO licenciaDAO;
    private final LicenciaMapper licenciaMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public List<LicenciaModel> buscarTodasPorConductorId(Long id) {
        log.info("Buscando todas las entidades Licencia con camión id: {}.", id);
        List<LicenciaModel> listado = licenciaDAO.findAllByConductorIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Licencia con camión id: " + id + ".");
        return listado;
    }

    @Override
    public List<LicenciaModel> buscarTodasPorConductorIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Licencia con camión id: {}, incluidas las eliminadas.", id);
        List<LicenciaModel> listado = licenciaDAO.findAllByConductorId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Licencia con camión id: " + id + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<LicenciaModel> buscarTodasPorVencimientoId(Long id) {
        log.info("Buscando todas las entidades Licencia con vencimiento id: {}.", id);
        List<LicenciaModel> listado = licenciaDAO.findAllByVencimientoIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Licencia con vencimiento id: " + id + ".");
        return listado;
    }

    @Override
    public List<LicenciaModel> buscarTodasPorVencimientoIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Licencia con vencimiento id: {}, incluidas las eliminadas.", id);
        List<LicenciaModel> listado = licenciaDAO.findAllByVencimientoId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Licencia con vencimiento id: " + id + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<LicenciaModel> buscarTodasPorNotas(String notas) {
        log.info("Buscando todas las entidades Licencia con notas: {}.", notas);
        List<LicenciaModel> listado = licenciaDAO.findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Licencia con notas: " + notas + ".");
        return listado;
    }

    @Override
    public List<LicenciaModel> buscarTodasPorNotasConEliminadas(String notas) {
        log.info("Buscando todas las entidades Licencia con notas: {}, incluidas las eliminadas.", notas);
        List<LicenciaModel> listado = licenciaDAO.findAllByNotasContainingIgnoreCase(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Licencia con notas: " + notas + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<LicenciaModel> buscarTodasPorVencimientoBetween(String inicio, String fin) {
        log.info("Buscando todas las entidades Licencia entre las fechas: {} y {}.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son válidas.");
        List<LicenciaModel> listado = licenciaDAO.findAllByVencimientoBetweenAndEliminadaIsNull( fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Licencia entre las fechas: " + inicio + " y " + fin + ".");
        return listado;
    }

    @Override
    public List<LicenciaModel> buscarTodasPorVencimientoBetweenConEliminadas(String inicio, String fin) {
        log.info("Buscando todas las entidades Licencia entre las fechas: {} y {}, con eliminadas.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son válidas.");
        List<LicenciaModel> listado = licenciaDAO.findAllByVencimientoBetween(fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Licencia entre las fechas: " + inicio + " y " + fin + ", con eliminadas.");
        return listado;
    }

    @Override
    public LicenciaModel buscarPorId(Long id) {
        log.info("Buscando la entidad Licencia con id: {}.", id);
        LicenciaModel licenciaModel = licenciaDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Licencia con id: " + id + "."));
        log.info("Se encontró una entidad Licencia con id: " + id + ".");
        return licenciaModel;
    }

    @Override
    public LicenciaModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Licencia con id: {}, incluidas las eliminadas.", id);
        LicenciaModel licenciaModel = licenciaDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Licencia con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Licencia con id: " + id + ", incluidas las eliminadas.");
        return licenciaModel;
    }

    @Override
    public List<LicenciaModel> buscarTodas() {
        log.info("Buscando todas las entidades Licencia.");
        List<LicenciaModel> listado = licenciaDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Licencia.");
        return listado;
    }

    @Override
    public List<LicenciaModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Licencia, incluidas las eliminadas.");
        List<LicenciaModel> listado = licenciaDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Licencia, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<LicenciaModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Licencia, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<LicenciaModel> slice = licenciaDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Licencia.");
        return slice;
    }

    @Override
    public Slice<LicenciaModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Licencia, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<LicenciaModel> slice = licenciaDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Licencia, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = licenciaDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Licencia.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = licenciaDAO.count();
        log.info("Existen {} entidades Licencia, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public LicenciaModel crear(LicenciaModel model) {
        log.info("Insertando la entidad LicenciaModel: {}.",  model);
        LicenciaModel licenciaModel = licenciaDAO.save(model);
        if (model.getId() == null) {
            licenciaModel.setCreada(Helper.getNow(""));
            licenciaModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad LicenciaModel.");
        } else {
            licenciaModel.setModificada(Helper.getNow(""));
            licenciaModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad LicenciaModel.");
        }
        return licenciaDAO.save(licenciaModel);
    }

    @Override
    public LicenciaModel guardar(LicenciaCreation creation) {
        log.info("Insertando la entidad LicenciaCreation: {}.",  creation);
        LicenciaModel licenciaModel = licenciaDAO.save(licenciaMapper.toEntity(creation));
        if (creation.getId() == null) {
            licenciaModel.setCreada(Helper.getNow(""));
            licenciaModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad LicenciaCreation.");
        } else {
            licenciaModel.setModificada(Helper.getNow(""));
            licenciaModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad LicenciaCreation.");
        }
        return licenciaDAO.save(licenciaModel);
    }

    @Override
    public LicenciaModel eliminar(Long id) {
        log.info("Eliminando la entidad Licencia con id: {}.", id);
        LicenciaModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Licencia con id: " + id + ", fue eliminada correctamente.");
        return licenciaDAO.save(objeto);
    }

    @Override
    public LicenciaModel reciclar(Long id) {
        log.info("Reciclando la entidad Licencia con id: {}.", id);
        LicenciaModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Licencia con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Licencia con id: " + id + ", fue reciclada correctamente.");
        return licenciaDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Licencia con id: {}.", id);
        LicenciaModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Licencia con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        licenciaDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
