package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.PresupuestoMapper;
import gloit.hiperionida.helios.mapper.creation.PresupuestoCreation;
import gloit.hiperionida.helios.mapper.creation.PresupuestoCreation;
import gloit.hiperionida.helios.model.PresupuestoModel;
import gloit.hiperionida.helios.model.PresupuestoModel;
import gloit.hiperionida.helios.repository.PresupuestoDAO;
import gloit.hiperionida.helios.service.PresupuestoService;
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
public class PresupuestoServiceImpl implements PresupuestoService {
    private final PresupuestoDAO presupuestoDAO;
    private final PresupuestoMapper presupuestoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public List<PresupuestoModel> buscarTodasPorCategoriaViajeId(Long id) {
        log.info("Buscando todas las entidades Presupuesto con id de CategoriaViaje: {}.", id);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByCategoriaViajeIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con id de CategoriaViaje: " + id + ".");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorCategoriaViajeIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Presupuesto con id de CategoriaViaje: {}, con eliminadas.", id);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByCategoriaViajeId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con id de CategoriaViaje: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorClienteId(Long id) {
        log.info("Buscando todas las entidades Presupuesto con id de Cliente: {}.", id);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByClienteIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con id de Cliente: " + id + ".");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorClienteIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Presupuesto con id de Cliente: {}, con eliminadas.", id);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByClienteId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con id de Cliente: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorDestinoId(Long id) {
        log.info("Buscando todas las entidades Presupuesto con id de Destino: {}.", id);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByDestinoIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con id de Destino: " + id + ".");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorDestinoIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Presupuesto con id de Destino: {}, con eliminadas.", id);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByDestinoId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con id de Destino: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorFechaEntreFechas(String inicio, String fin) {
        log.info("Buscando todas las entidades entre las fechas: {} y {}.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son válidas.");
        List<PresupuestoModel> listado = presupuestoDAO.findAllByFechaFechaBetweenAndEliminadaIsNull( fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto entre las fechas: " + inicio + " y " + fin + ".");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorFechaEntreFechasConEliminadas(String inicio, String fin) {
        log.info("Buscando todas las entidades entre las fechas: {} y {}, incluidas las eliminadas.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son válidas, incluidas las eliminadas.");
        List<PresupuestoModel> listado = presupuestoDAO.findAllByFechaFechaBetween( fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto entre las fechas: " + inicio + " y " + fin + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorNotas(String nota) {
        log.info("Buscando todas las entidades Presupuesto con notas: {}.", nota);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(nota);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con notas: " + nota + ".");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorNotasConEliminadas(String nota) {
        log.info("Buscando todas las entidades Presupuesto con notas: {}, con eliminadas.", nota);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByNotasContainingIgnoreCase(nota);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con notas: " + nota + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorOrigenId(Long id) {
        log.info("Buscando todas las entidades Presupuesto con id de Origen: {}.", id);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByOrigenIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con id de Origen: " + id + ".");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorOrigenIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Presupuesto con id de Origen: {}, con eliminadas.", id);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByOrigenId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con id de Origen: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorRangoCantidadTransportada(Integer min, Integer max) {
        log.info("Buscando todas las entidades Presupuesto con un rango de cantidad transportada entre: {} y {}.", min, max);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByCantidadTransportadaBetweenAndEliminadaIsNull(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con un rango de cantidad transportada entre: " + min + " y " + max + ".");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorRangoCantidadTransportadaConEliminadas(Integer min, Integer max) {
        log.info("Buscando todas las entidades Presupuesto con un rango de cantidad transportada entre: {} y {}, con eliminadas.", min, max);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByCantidadTransportadaBetween(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con un cantidad transportada entre: " + min + " y " + max + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorRangoKmCargado(Double min, Double max) {
        log.info("Buscando todas las entidades Presupuesto con un rango de km cargado entre: {} y {}.", min, max);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByKmCargadoBetweenAndEliminadaIsNull(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con un rango de km cargado entre: " + min + " y " + max + ".");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorRangoKmCargadoConEliminadas(Double min, Double max) {
        log.info("Buscando todas las entidades Presupuesto con un rango de km cargado entre: {} y {}, con eliminadas.", min, max);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByKmCargadoBetween(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con un rango de km cargado entre: " + min + " y " + max + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorRangoValorKm(Double min, Double max) {
        log.info("Buscando todas las entidades Presupuesto con un rango de valor del kilometro entre: {} y {}.", min, max);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByValorKmBetweenAndEliminadaIsNull(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con un rango de valor del kilometro entre: " + min + " y " + max + ".");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorRangoValorKmConEliminadas(Double min, Double max) {
        log.info("Buscando todas las entidades Presupuesto con un rango de valor del kilometro entre: {} y {}, con eliminadas.", min, max);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByValorKmBetween(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con un rango de valor del kilometro entre: " + min + " y " + max + ", con eliminadas.");
        return listado;
    }

    @Override
    public PresupuestoModel buscarProximo() {
        log.info("Buscando la proxima entidad Presupuesto.");

        return presupuestoDAO.findNextDate(Helper.getNow("")).orElseThrow(() -> new DatosInexistentesException("No se encontró el proximo Presupuesto"));
    }

    @Override
    public PresupuestoModel buscarUltimo() {
        log.info("Buscando la ultima entidad Presupuesto.");

        return presupuestoDAO.findFirstByOrderByIdDesc().orElseThrow(() -> new DatosInexistentesException("No se encontró el ultimo Presupuesto"));
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorClienteNombre(String nombre) {
        log.info("Buscando todas las entidades Presupuesto con nombre de Cliente: {}.", nombre);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByClienteNombreContainingIgnoreCaseAndEliminadaIsNull(nombre);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con nombre de Cliente: " + nombre + ".");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorClienteNombreConEliminadas(String nombre) {
        log.info("Buscando todas las entidades Presupuesto con nombre de Cliente: {}, con eliminadas.", nombre);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByClienteNombreContainingIgnoreCase(nombre);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con nombre de Cliente: " + nombre + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorOrigenDireccion(String direccion) {
        log.info("Buscando todas las entidades Presupuesto con Origen: {}.", direccion);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByOrigenDireccionContainingIgnoreCaseAndEliminadaIsNull(direccion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con Origen: " + direccion + ".");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorOrigenDireccionConEliminadas(String direccion) {
        log.info("Buscando todas las entidades Presupuesto con Origen: {}, con eliminadas.", direccion);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByOrigenDireccionContainingIgnoreCase(direccion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con Origen: " + direccion + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorDestinoDireccion(String direccion) {
        log.info("Buscando todas las entidades Presupuesto con Destino: {}.", direccion);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByDestinoDireccionContainingIgnoreCaseAndEliminadaIsNull(direccion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con Destino: " + direccion + ".");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasPorDestinoDireccionConEliminadas(String direccion) {
        log.info("Buscando todas las entidades Presupuesto con Destino: {}, con eliminadas.", direccion);
        List<PresupuestoModel> listado = presupuestoDAO.findAllByDestinoDireccionContainingIgnoreCase(direccion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto con Destino: " + direccion + ", con eliminadas.");
        return listado;
    }

    @Override
    public PresupuestoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Presupuesto con id: {}.", id);
        PresupuestoModel presupuestoModel = presupuestoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Presupuesto con id: " + id + "."));
        log.info("Se encontró una entidad Presupuesto con id: " + id + ".");
        return presupuestoModel;
    }

    @Override
    public PresupuestoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Presupuesto con id: {}, incluidas las eliminadas.", id);
        PresupuestoModel presupuestoModel = presupuestoDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Presupuesto con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Presupuesto con id: " + id + ", incluidas las eliminadas.");
        return presupuestoModel;
    }

    @Override
    public List<PresupuestoModel> buscarTodas() {
        log.info("Buscando todas las entidades Presupuesto.");
        List<PresupuestoModel> listado = presupuestoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto.");
        return listado;
    }

    @Override
    public List<PresupuestoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Presupuesto, incluidas las eliminadas.");
        List<PresupuestoModel> listado = presupuestoDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<PresupuestoModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Presupuesto, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<PresupuestoModel> slice = presupuestoDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto.");
        return slice;
    }

    @Override
    public Slice<PresupuestoModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Presupuesto, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<PresupuestoModel> slice = presupuestoDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Presupuesto, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = presupuestoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Presupuesto.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = presupuestoDAO.count();
        log.info("Existen {} entidades Presupuesto, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public PresupuestoModel crear(PresupuestoModel model) {
        log.info("Insertando la entidad PresupuestoModel: {}.",  model);
        PresupuestoModel presupuestoModel = presupuestoDAO.save(model);
        if (model.getId() == null) {
            presupuestoModel.setCreada(Helper.getNow(""));
            presupuestoModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad PresupuestoModel.");
        } else {
            presupuestoModel.setModificada(Helper.getNow(""));
            presupuestoModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad PresupuestoModel.");
        }
        return presupuestoDAO.save(presupuestoModel);
    }

    @Override
    public PresupuestoModel guardar(PresupuestoCreation creation) {
        log.info("Insertando la entidad PresupuestoCreation: {}.",  creation);
        PresupuestoModel presupuestoModel = presupuestoDAO.save(presupuestoMapper.toEntity(creation));
        if (creation.getId() == null) {
            presupuestoModel.setCreada(Helper.getNow(""));
            presupuestoModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad PresupuestoCreation.");
        } else {
            presupuestoModel.setModificada(Helper.getNow(""));
            presupuestoModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad PresupuestoCreation.");
        }
        return presupuestoDAO.save(presupuestoModel);
    }

    @Override
    public PresupuestoModel eliminar(Long id) {
        log.info("Eliminando la entidad Presupuesto con id: {}.", id);
        PresupuestoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Presupuesto con id: " + id + ", fue eliminada correctamente.");
        return presupuestoDAO.save(objeto);
    }

    @Override
    public PresupuestoModel reciclar(Long id) {
        log.info("Reciclando la entidad Presupuesto con id: {}.", id);
        PresupuestoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Presupuesto con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Presupuesto con id: " + id + ", fue reciclada correctamente.");
        return presupuestoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Presupuesto con id: {}.", id);
        PresupuestoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Presupuesto con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        presupuestoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
