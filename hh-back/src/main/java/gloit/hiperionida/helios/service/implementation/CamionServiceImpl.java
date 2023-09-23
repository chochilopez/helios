package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.CamionMapper;
import gloit.hiperionida.helios.mapper.creation.CamionCreation;
import gloit.hiperionida.helios.model.CamionModel;
import gloit.hiperionida.helios.repository.CamionDAO;
import gloit.hiperionida.helios.service.CamionService;
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
public class CamionServiceImpl implements CamionService {
    private final CamionDAO camionDAO;
    private final CamionMapper camionMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public CamionModel buscarPorId(Long id) {
        log.info("Buscando la entidad Camion con id: {}.", id);
        CamionModel camionModel = camionDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Camion con id: " + id + "."));
        String mensaje = "Se encontro una entidad Camion.";
        log.info(mensaje);
        return camionModel;
    }

    @Override
    public CamionModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Camion con id: {}, incluidas las eliminadas.", id);
        CamionModel camionModel = camionDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Camion con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Camion con id: " + id + ".");
        return camionModel;
    }

    @Override
    public List<CamionModel> buscarTodas() {
        log.info("Buscando todas las entidades Camion.");
        List<CamionModel> listado = camionDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Camion.");
        log.info("Se encontraron {} entidades Camion.", listado.size());
        return listado;
    }

    @Override
    public List<CamionModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Camion, incluidas las eliminadas.");
        List<CamionModel> listado = camionDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Camion, incluidas las eliminadas.");
        log.info("Se encontraron {} entidades Camion, incluidas las eliminadas.", listado.size());
        return listado;
    }

    @Override
    public Slice<CamionModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Camion, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<CamionModel> slice = camionDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Camion.");
        return slice;
    }

    @Override
    public Slice<CamionModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Camion, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<CamionModel> slice = camionDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Camion, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = camionDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Camion.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = camionDAO.count();
        log.info("Existen {} entidades Camion, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public CamionModel guardar(CamionCreation creation) {
        log.info("Insertando la entidad Camion: {}.",  creation);
        CamionModel camionModel = camionDAO.save(camionMapper.toEntity(creation));
        if (creation.getId() == null) {
            camionModel.setCreada(Helper.getNow(""));
            camionModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            camionModel.setModificada(Helper.getNow(""));
            camionModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return camionDAO.save(camionModel);
    }

    @Override
    public CamionModel eliminar(Long id) {
        log.info("Eliminando la entidad Camion con id: {}.", id);
        CamionModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Camion con id: " + id + ", fue eliminada correctamente.");
        return camionDAO.save(objeto);
    }

    @Override
    public CamionModel reciclar(Long id) {
        log.info("Reciclando la entidad Camion con id: {}.", id);
        CamionModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Camion con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Camion con id: " + id + ", fue reciclada correctamente.");
        return camionDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Camion con id: {}.", id);
        CamionModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Camion con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        camionDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
