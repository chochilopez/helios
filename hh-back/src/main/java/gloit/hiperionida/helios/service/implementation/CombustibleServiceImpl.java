package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.CombustibleMapper;
import gloit.hiperionida.helios.mapper.creation.CombustibleCreation;
import gloit.hiperionida.helios.model.CombustibleModel;
import gloit.hiperionida.helios.repository.CombustibleDAO;
import gloit.hiperionida.helios.service.CombustibleService;
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
public class CombustibleServiceImpl implements CombustibleService {
    private final CombustibleDAO combustibleDAO;
    private final CombustibleMapper combustibleMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public List<CombustibleModel> buscarTodasPorCajaId(Long id) {
        log.info("Buscando todas las entidades Combustible con id de Caja: {}.", id);
        List<CombustibleModel> listado = combustibleDAO.findAllByCajaIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con id de Caja: " + id + ".");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorCajaIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Combustible con id de Caja: {}, con eliminadas.", id);
        List<CombustibleModel> listado = combustibleDAO.findAllByCajaId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con id de Caja: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorCamionId(Long id) {
        log.info("Buscando todas las entidades Combustible con id de Camion: {}.", id);
        List<CombustibleModel> listado = combustibleDAO.findAllByCamionIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con id de Camion: " + id + ".");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorCamionIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Combustible con id de Camion: {}, con eliminadas.", id);
        List<CombustibleModel> listado = combustibleDAO.findAllByCamionId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con id de Camion: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorConductorId(Long id) {
        log.info("Buscando todas las entidades Combustible con id de Conductor: {}.", id);
        List<CombustibleModel> listado = combustibleDAO.findAllByConductorIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con id de Conductor: " + id + ".");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorConductorIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Combustible con id de Conductor: {}, con eliminadas.", id);
        List<CombustibleModel> listado = combustibleDAO.findAllByConductorId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con id de Conductor: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorFechaEntre(String inicio, String fin) {
        log.info("Buscando todas las entidades Combustible entre las fechas: {} y {}.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son válidas.");
        List<CombustibleModel> listado = combustibleDAO.findAllByFechaBetweenAndEliminadaIsNull( fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible entre las fechas: " + inicio + " y " + fin + ".");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorFechaEntreConEliminadas(String inicio, String fin) {
        log.info("Buscando todas las entidades Combustible entre las fechas: {} y {}, con eliminadas.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son válidas.");
        List<CombustibleModel> listado = combustibleDAO.findAllByFechaBetween(fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades entre las fechas: " + inicio + " y " + fin + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorNotasContainingIgnoreCase(String notas) {
        log.info("Buscando todas las entidades Combustible con notas: {}.", notas);
        List<CombustibleModel> listado = combustibleDAO.findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con notas: " + notas + ".");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorNotasContainingIgnoreCaseConEliminadas(String notas) {
        log.info("Buscando todas las entidades Combustible con notas: {}, con eliminadas.", notas);
        List<CombustibleModel> listado = combustibleDAO.findAllByNotasContainingIgnoreCase(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con notas: " + notas + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorKilometrosEntre(Double min, Double max) {
        log.info("Buscando todas las entidades Combustible con un rango de Kilometros entre: {} y {}.", min, max);
        List<CombustibleModel> listado = combustibleDAO.findAllByKilometrosBetweenAndEliminadaIsNull(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con un rango de Kilometros cargado entre: " + min + " y " + max + ".");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorKilometrosEntreConEliminadas(Double min, Double max) {
        log.info("Buscando todas las entidades Combustible con un rango de Kilometros entre: {} y {}, con eliminadas.", min, max);
        List<CombustibleModel> listado = combustibleDAO.findAllByKilometrosBetween(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con un rango de Kilometros cargado entre: " + min + " y " + max + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorLitrosEntre(Double min, Double max) {
        log.info("Buscando todas las entidades Combustible con un rango de Litros entre: {} y {}.", min, max);
        List<CombustibleModel> listado = combustibleDAO.findAllByLitrosBetweenAndEliminadaIsNull(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con un rango de Litros cargado entre: " + min + " y " + max + ".");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorLitrosEntreConEliminadas(Double min, Double max) {
        log.info("Buscando todas las entidades Combustible con un rango de Litros entre: {} y {}, con eliminadas.", min, max);
        List<CombustibleModel> listado = combustibleDAO.findAllByLitrosBetween(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con un rango de Litros cargado entre: " + min + " y " + max + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorPrecioEntre(Double min, Double max) {
        log.info("Buscando todas las entidades Combustible con un rango de Precio entre: {} y {}.", min, max);
        List<CombustibleModel> listado = combustibleDAO.findAllByPrecioBetweenAndEliminadaIsNull(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con un rango de Precio cargado entre: " + min + " y " + max + ".");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorPrecioEntreConEliminadas(Double min, Double max) {
        log.info("Buscando todas las entidades Combustible con un rango de Precio entre: {} y {}, con eliminadas.", min, max);
        List<CombustibleModel> listado = combustibleDAO.findAllByPrecioBetween(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con un rango de Precio cargado entre: " + min + " y " + max + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorProveedorId(Long id) {
        log.info("Buscando todas las entidades Combustible con id de Proveedor: {}.", id);
        List<CombustibleModel> listado = combustibleDAO.findAllByProveedorIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con id de Proveedor: " + id + ".");
        return listado;
    }

    @Override
    public List<CombustibleModel> buscarTodasPorProveedorIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Combustible con id de Proveedor: {}, con eliminadas.", id);
        List<CombustibleModel> listado = combustibleDAO.findAllByProveedorId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Combustible con id de Proveedor: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public CombustibleModel buscarPorId(Long id) {
        log.info("Buscando la entidad Combustible con id: {}.", id);
        CombustibleModel combustibleModel = combustibleDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Combustible con id: " + id + "."));
        log.info("Se encontró una entidad Combustible con id: " + id + ".");
        return combustibleModel;
    }

    @Override
    public CombustibleModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Combustible con id: {}, incluidas las eliminadas.", id);
        CombustibleModel combustibleModel = combustibleDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Combustible con id: " + id +", incluidas las eliminadas."));
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
    public CombustibleModel crear(CombustibleModel model) {
        log.info("Insertando la entidad CombustibleModel: {}.",  model);
        CombustibleModel combustibleModel = combustibleDAO.save(model);
        if (model.getId() == null) {
            combustibleModel.setCreada(Helper.getNow(""));
            combustibleModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad CombustibleModel.");
        } else {
            combustibleModel.setModificada(Helper.getNow(""));
            combustibleModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad CombustibleModel.");
        }
        return combustibleDAO.save(combustibleModel);
    }

    @Override
    public CombustibleModel guardar(CombustibleCreation creation) {
        log.info("Insertando la entidad CombustibleCreation: {}.",  creation);
        CombustibleModel combustibleModel = combustibleDAO.save(combustibleMapper.toEntity(creation));
        if (creation.getId() == null) {
            combustibleModel.setCreada(Helper.getNow(""));
            combustibleModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad CombustibleCreation.");
        } else {
            combustibleModel.setModificada(Helper.getNow(""));
            combustibleModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad CombustibleCreation.");
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
