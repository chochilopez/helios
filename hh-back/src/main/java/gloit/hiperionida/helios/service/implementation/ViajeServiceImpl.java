package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.ViajeMapper;
import gloit.hiperionida.helios.mapper.creation.ViajeCreation;
import gloit.hiperionida.helios.model.PresupuestoModel;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.repository.ViajeDAO;
import gloit.hiperionida.helios.service.ViajeService;
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
public class ViajeServiceImpl implements ViajeService {
    private final ViajeDAO viajeDAO;
    private final ViajeMapper viajeMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public Double sumarTodasCantidadTrasnportada() {
        log.info("Sumando todas las cantidades transportadas.");
        Double cantidad = viajeDAO.sumarCantidadTrasnportada();
        log.info("Suma de todas las cantidades transportadas: {}.", cantidad);

        return cantidad;
    }

    @Override
    public Double sumarTodasKmCargado() {
        log.info("Sumando todos las kilometros viajados cargado.");
        Double cantidad = viajeDAO.sumarKmCargado();
        log.info("Suma de todos los kilometros viajados cargado: {}.", cantidad);

        return cantidad;
    }

    @Override
    public Double sumarTodasKmVacio() {
        log.info("Sumando todos las kilometros viajados vacio.");
        Double cantidad = viajeDAO.sumarKmVacio();
        log.info("Suma de todos los kilometros viajados vacio: {}.", cantidad);

        return cantidad;
    }

    @Override
    public ViajeModel buscarUltimoViaje() {
        log.info("Buscando la ultima entidad Viaje.");

        return viajeDAO.findFirstByOrderByIdDesc().orElseThrow(() -> new DatosInexistentesException("No se encontro el ultimo Viaje"));
    }

    @Override
    public List<ViajeModel> buscarTodasPorFechaEntreFechas(String inicio, String fin) {
        log.info("Buscando todas las entidades entre las fechas: {} y {}.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son validas.");
        List<ViajeModel> listado = viajeDAO.findAllByFechaFechaBetweenAndEliminadaIsNull( fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje entre las fechas: " + inicio + " y " + fin + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorFechaEntreFechasConEliminadas(String inicio, String fin) {
        log.info("Buscando todas las entidades entre las fechas: {} y {}, con eliminadas.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son validas.");
        List<ViajeModel> listado = viajeDAO.findAllByFechaFechaBetween(fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades entre las fechas: " + inicio + " y " + fin + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorAcopladoId(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Acoplado: {}.", id);
        List<ViajeModel> listado = viajeDAO.findAllByAcopladoIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Acoplado: " + id + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorAcopladoIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Acoplado: {}, con eliminadas.", id);
        List<ViajeModel> listado = viajeDAO.findAllByAcopladoId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Acoplado: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorCamionId(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Camion: {}.", id);
        List<ViajeModel> listado = viajeDAO.findAllByCamionIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Camion: " + id + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorCamionIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Camion: {}, con eliminadas.", id);
        List<ViajeModel> listado = viajeDAO.findAllByCamionId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Camion: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorCargaId(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Carga: {}.", id);
        List<ViajeModel> listado = viajeDAO.findAllByCargaIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Carga: " + id + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorCargaIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Carga: {}, con eliminadas.", id);
        List<ViajeModel> listado = viajeDAO.findAllByCargaId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Carga: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorCategoriaViajeId(Long id) {
        log.info("Buscando todas las entidades Viaje con id de CategoriaViaje: {}.", id);
        List<ViajeModel> listado = viajeDAO.findAllByCategoriaViajeIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de CategoriaViaje: " + id + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorCategoriaViajeIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Viaje con id de CategoriaViaje: {}, con eliminadas.", id);
        List<ViajeModel> listado = viajeDAO.findAllByCategoriaViajeId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de CategoriaViaje: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorConductorId(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Conductor: {}.", id);
        List<ViajeModel> listado = viajeDAO.findAllByConductorIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Conductor: " + id + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorConductorIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Conductor: {}, con eliminadas.", id);
        List<ViajeModel> listado = viajeDAO.findAllByConductorId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Conductor: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorCompradorId(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Comprador: {}.", id);
        List<ViajeModel> listado = viajeDAO.findAllByCompradorIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Comprador: " + id + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorCompradorIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Comprador: {}, con eliminadas.", id);
        List<ViajeModel> listado = viajeDAO.findAllByCompradorId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Comprador: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorIntermediarioId(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Intermediario: {}.", id);
        List<ViajeModel> listado = viajeDAO.findAllByIntermediarioIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Intermediario: " + id + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorIntermediarioIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Intermediario: {}, con eliminadas.", id);
        List<ViajeModel> listado = viajeDAO.findAllByIntermediarioId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Intermediario: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorRangoKmCargado(Double min, Double max) {
        log.info("Buscando todas las entidades Viaje con un rango de kilometros entre: {} y {}.", min, max);
        List<ViajeModel> listado = viajeDAO.findAllByKmCargadoBetweenAndEliminadaIsNull(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con un rango de kilometros cargado entre: " + min + " y " + max + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorRangoKmCargadoConEliminadas(Double min, Double max) {
        log.info("Buscando todas las entidades Viaje con un rango de kilometros entre: {} y {}, con eliminadas.", min, max);
        List<ViajeModel> listado = viajeDAO.findAllByKmCargadoBetween(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con un rango de kilometros cargado entre: " + min + " y " + max + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorRangoKmVacio(Double min, Double max) {
        log.info("Buscando todas las entidades Viaje con un rango de kilometros entre: {} y {}.", min, max);
        List<ViajeModel> listado = viajeDAO.findAllByKmVacioBetweenAndEliminadaIsNull(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con un rango de kilometros vacio entre: " + min + " y " + max + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorRangoKmVacioConEliminadas(Double min, Double max) {
        log.info("Buscando todas las entidades Viaje con un rango de kilometros entre: {} y {}, con eliminadas.", min, max);
        List<ViajeModel> listado = viajeDAO.findAllByKmVacioBetween(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con un rango de kilometros vacio entre: " + min + " y " + max + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorNotas(String nota) {
        log.info("Buscando todas las entidades Viaje con notas: {}.", nota);
        List<ViajeModel> listado = viajeDAO.findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(nota);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con notas: " + nota + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorNotasConEliminadas(String nota) {
        log.info("Buscando todas las entidades Viaje con notas: {}, con eliminadas.", nota);
        List<ViajeModel> listado = viajeDAO.findAllByNotasContainingIgnoreCase(nota);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con notas: " + nota + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorGuia(String guia) {
        log.info("Buscando todas las entidades Viaje con notas: {}.", guia);
        List<ViajeModel> listado = viajeDAO.findAllByGuiaContainingIgnoreCaseAndEliminadaIsNull(guia);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con notas: " + guia + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorGuiaConEliminadas(String guia) {
        log.info("Buscando todas las entidades Viaje con notas: {}, con eliminadas.", guia);
        List<ViajeModel> listado = viajeDAO.findAllByGuiaContainingIgnoreCase(guia);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con notas: " + guia + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorRangoCantidadTransportada(Integer min, Integer max) {
        log.info("Buscando todas las entidades Viaje con un rango de cantidad transportada entre: {} y {}.", min, max);
        List<ViajeModel> listado = viajeDAO.findAllByCantidadTransportadaBetweenAndEliminadaIsNull(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con un rango de cantidad transportada entre: " + min + " y " + max + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorRangoCantidadTransportadaConEliminadas(Integer min, Integer max) {
        log.info("Buscando todas las entidades Viaje con un rango de cantidad transportada entre: {} y {}, con eliminadas.", min, max);
        List<ViajeModel> listado = viajeDAO.findAllByCantidadTransportadaBetween(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con un cantidad transportada entre: " + min + " y " + max + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorRangoNeto(Double min, Double max) {
        log.info("Buscando todas las entidades Viaje con un rango de peso neto entre: {} y {}.", min, max);
        List<ViajeModel> listado = viajeDAO.findAllByNetoBetweenAndEliminadaIsNull(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con un rango de peso neto entre: " + min + " y " + max + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorRangoNetoConEliminadas(Double min, Double max) {
        log.info("Buscando todas las entidades Viaje con un rango de peso neto entre: {} y {}, con eliminadas.", min, max);
        List<ViajeModel> listado = viajeDAO.findAllByNetoBetween(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con un rango de peso neto entre: " + min + " y " + max + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorRangoValorKm(Double min, Double max) {
        log.info("Buscando todas las entidades Viaje con un rango de valor del kilometro entre: {} y {}.", min, max);
        List<ViajeModel> listado = viajeDAO.findAllByValorKmBetweenAndEliminadaIsNull(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con un rango de valor del kilometro entre: " + min + " y " + max + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorRangoValorKmConEliminadas(Double min, Double max) {
        log.info("Buscando todas las entidades Viaje con un rango de valor del kilometro entre: {} y {}, con eliminadas.", min, max);
        List<ViajeModel> listado = viajeDAO.findAllByValorKmBetween(min, max);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con un rango de valor del kilometro entre: " + min + " y " + max + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorVendedorId(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Vendedor: {}.", id);
        List<ViajeModel> listado = viajeDAO.findAllByVendedorIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Vendedor: " + id + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorVendedorIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Vendedor: {}, con eliminadas.", id);
        List<ViajeModel> listado = viajeDAO.findAllByVendedorId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Vendedor: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorOrigenId(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Origen: {}.", id);
        List<ViajeModel> listado = viajeDAO.findAllByOrigenIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Origen: " + id + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorOrigenIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Origen: {}, con eliminadas.", id);
        List<ViajeModel> listado = viajeDAO.findAllByOrigenId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Origen: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorDestinoId(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Destino: {}.", id);
        List<ViajeModel> listado = viajeDAO.findAllByDestinoIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Destino: " + id + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorDestinoIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Viaje con id de Destino: {}, con eliminadas.", id);
        List<ViajeModel> listado = viajeDAO.findAllByDestinoId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con id de Destino: " + id + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorCompradorNombre(String nombre) {
        log.info("Buscando todas las entidades Viaje con nombre de Comprador: {}.", nombre);
        List<ViajeModel> listado = viajeDAO.findAllByCompradorNombreContainingIgnoreCaseAndEliminadaIsNull(nombre);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con nombre de Comprador: " + nombre + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorCompradorNombreConEliminadas(String nombre) {
        log.info("Buscando todas las entidades Viaje con nombre de Comprador: {}, con eliminadas.", nombre);
        List<ViajeModel> listado = viajeDAO.findAllByCompradorNombreContainingIgnoreCase(nombre);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con nombre de Comprador: " + nombre + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorVendedorNombre(String nombre) {
        log.info("Buscando todas las entidades Viaje con nombre de Vendedor: {}.", nombre);
        List<ViajeModel> listado = viajeDAO.findAllByVendedorNombreContainingIgnoreCaseAndEliminadaIsNull(nombre);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con nombre de Vendedor: " + nombre + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorVendedorNombreConEliminadas(String nombre) {
        log.info("Buscando todas las entidades Viaje con nombre de Vendedor: {}, con eliminadas.", nombre);
        List<ViajeModel> listado = viajeDAO.findAllByVendedorNombreContainingIgnoreCase(nombre);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con nombre de Vendedor: " + nombre + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorOrigenDireccion(String direccion) {
        log.info("Buscando todas las entidades Viaje con Origen: {}.", direccion);
        List<ViajeModel> listado = viajeDAO.findAllByOrigenDireccionContainingIgnoreCaseAndEliminadaIsNull(direccion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con Origen: " + direccion + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorOrigenDireccionConEliminadas(String direccion) {
        log.info("Buscando todas las entidades Viaje con Origen: {}, con eliminadas.", direccion);
        List<ViajeModel> listado = viajeDAO.findAllByOrigenDireccionContainingIgnoreCase(direccion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con Origen: " + direccion + ", con eliminadas.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorDestinoDireccion(String direccion) {
        log.info("Buscando todas las entidades Viaje con Destino: {}.", direccion);
        List<ViajeModel> listado = viajeDAO.findAllByDestinoDireccionContainingIgnoreCaseAndEliminadaIsNull(direccion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con Destino: " + direccion + ".");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasPorDestinoDireccionConEliminadas(String direccion) {
        log.info("Buscando todas las entidades Viaje con Destino: {}, con eliminadas.", direccion);
        List<ViajeModel> listado = viajeDAO.findAllByDestinoDireccionContainingIgnoreCase(direccion);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje con Destino: " + direccion + ", con eliminadas.");
        return listado;
    }

    @Override
    public ViajeModel buscarPorId(Long id) {
        log.info("Buscando la entidad Viaje con id: {}.", id);
        ViajeModel viajeModel = viajeDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Viaje con id: " + id + "."));
        log.info("Se encontro una entidad Viaje con id: " + id + ".");
        return viajeModel;
    }

    @Override
    public ViajeModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Viaje con id: {}, incluidas las eliminadas.", id);
        ViajeModel viajeModel = viajeDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Viaje con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Viaje con id: " + id + ", incluidas las eliminadas.");
        return viajeModel;
    }

    @Override
    public List<ViajeModel> buscarTodas() {
        log.info("Buscando todas las entidades Viaje.");
        List<ViajeModel> listado = viajeDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje.");
        return listado;
    }

    @Override
    public List<ViajeModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Viaje, incluidas las eliminadas.");
        List<ViajeModel> listado = viajeDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<ViajeModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Viaje, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<ViajeModel> slice = viajeDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje.");
        return slice;
    }

    @Override
    public Slice<ViajeModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Viaje, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<ViajeModel> slice = viajeDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Viaje, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = viajeDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Viaje.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = viajeDAO.count();
        log.info("Existen {} entidades Viaje, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ViajeModel guardar(ViajeCreation creation) {
        log.info("Insertando la entidad Viaje: {}.",  creation);
        ViajeModel viajeModel = viajeDAO.save(viajeMapper.toEntity(creation));
        if (creation.getId() == null) {
            viajeModel.setCreada(Helper.getNow(""));
            viajeModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            viajeModel.setModificada(Helper.getNow(""));
            viajeModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return viajeDAO.save(viajeModel);
    }

    @Override
    public ViajeModel eliminar(Long id) {
        log.info("Eliminando la entidad Viaje con id: {}.", id);
        ViajeModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Viaje con id: " + id + ", fue eliminada correctamente.");
        return viajeDAO.save(objeto);
    }

    @Override
    public ViajeModel reciclar(Long id) {
        log.info("Reciclando la entidad Viaje con id: {}.", id);
        ViajeModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Viaje con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Viaje con id: " + id + ", fue reciclada correctamente.");
        return viajeDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Viaje con id: {}.", id);
        ViajeModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Viaje con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        viajeDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
