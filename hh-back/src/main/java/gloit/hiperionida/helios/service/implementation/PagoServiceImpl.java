package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.PagoMapper;
import gloit.hiperionida.helios.mapper.creation.PagoCreation;
import gloit.hiperionida.helios.model.PagoModel;
import gloit.hiperionida.helios.repository.PagoDAO;
import gloit.hiperionida.helios.service.PagoService;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.CustomDataNotFoundException;
import gloit.hiperionida.helios.util.exception.CustomObjectNotDeletedException;
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
public class PagoServiceImpl implements PagoService {
    private final PagoDAO pagoDAO;
    private final PagoMapper pagoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public PagoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Pago con id: {}.", id);
        PagoModel pagoModel = pagoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Pago con id: " + id + "."));
        String mensaje = "Se encontro una entidad Pago.";
        log.info(mensaje);
        return pagoModel;
    }

    @Override
    public PagoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Pago con id: {}, incluidas las eliminadas.", id);
        PagoModel pagoModel = pagoDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Pago con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Pago con id: " + id + ".");
        return pagoModel;
    }

    @Override
    public List<PagoModel> buscarTodas() {
        log.info("Buscando todas las entidades Pago.");
        List<PagoModel> listado = pagoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Pago.");
        return listado;
    }

    @Override
    public List<PagoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Pago, incluidas las eliminadas.");
        List<PagoModel> listado = pagoDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Pago, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<PagoModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Pago, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<PagoModel> slice = pagoDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Pago.");
        return slice;
    }

    @Override
    public Slice<PagoModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Pago, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<PagoModel> slice = pagoDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Pago, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = pagoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Pago.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = pagoDAO.count();
        log.info("Existen {} entidades Pago, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public PagoModel guardar(PagoCreation creation) {
        log.info("Insertando la entidad Pago: {}.",  creation);
        PagoModel pagoModel = pagoDAO.save(pagoMapper.toEntity(creation));
        if (creation.getId() == null) {
            pagoModel.setCreada(Helper.getNow(""));
            pagoModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            pagoModel.setModificada(Helper.getNow(""));
            pagoModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return pagoDAO.save(pagoModel);
    }

    @Override
    public PagoModel eliminar(Long id) {
        log.info("Eliminando la entidad Pago con id: {}.", id);
        PagoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Pago con id: " + id + ", fue eliminada correctamente.");
        return pagoDAO.save(objeto);
    }

    @Override
    public PagoModel reciclar(Long id) {
        log.info("Reciclando la entidad Pago con id: {}.", id);
        PagoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Pago con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Pago con id: " + id + ", fue reciclada correctamente.");
        return pagoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Pago con id: {}.", id);
        PagoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Pago con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        pagoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
