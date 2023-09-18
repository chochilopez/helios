package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.ReciboMapper;
import gloit.hiperionida.helios.mapper.creation.ReciboCreation;
import gloit.hiperionida.helios.model.ReciboModel;
import gloit.hiperionida.helios.repository.ReciboDAO;
import gloit.hiperionida.helios.service.ReciboService;
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
public class ReciboServiceImpl implements ReciboService {
    private final ReciboDAO reciboDAO;
    private final ReciboMapper reciboMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public ReciboModel buscarPorId(Long id) {
        log.info("Buscando la entidad Recibo con id: {}.", id);
        ReciboModel reciboModel = reciboDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Recibo con id: " + id + "."));
        String mensaje = "Se encontro una entidad Recibo.";
        log.info(mensaje);
        return reciboModel;
    }

    @Override
    public ReciboModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Recibo con id: {}, incluidas las eliminadas.", id);
        ReciboModel reciboModel = reciboDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Recibo con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Recibo con id: " + id + ".");
        return reciboModel;
    }

    @Override
    public List<ReciboModel> buscarTodas() {
        log.info("Buscando todas las entidades Recibo.");
        List<ReciboModel> listado = reciboDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Recibo.");
        return listado;
    }

    @Override
    public List<ReciboModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Recibo, incluidas las eliminadas.");
        List<ReciboModel> listado = reciboDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Recibo, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<ReciboModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Recibo, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<ReciboModel> slice = reciboDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Recibo.");
        return slice;
    }

    @Override
    public Slice<ReciboModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Recibo, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<ReciboModel> slice = reciboDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Recibo, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = reciboDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Recibo.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = reciboDAO.count();
        log.info("Existen {} entidades Recibo, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ReciboModel guardar(ReciboCreation creation) {
        log.info("Insertando la entidad Recibo: {}.",  creation);
        ReciboModel reciboModel = reciboDAO.save(reciboMapper.toEntity(creation));
        if (creation.getId() == null) {
            reciboModel.setCreada(Helper.getNow(""));
            reciboModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            reciboModel.setModificada(Helper.getNow(""));
            reciboModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return reciboDAO.save(reciboModel);
    }

    @Override
    public ReciboModel eliminar(Long id) {
        log.info("Eliminando la entidad Recibo con id: {}.", id);
        ReciboModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Recibo con id: " + id + ", fue eliminada correctamente.");
        return reciboDAO.save(objeto);
    }

    @Override
    public ReciboModel reciclar(Long id) {
        log.info("Reciclando la entidad Recibo con id: {}.", id);
        ReciboModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Recibo con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Recibo con id: " + id + ", fue reciclada correctamente.");
        return reciboDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Recibo con id: {}.", id);
        ReciboModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Recibo con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        reciboDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
