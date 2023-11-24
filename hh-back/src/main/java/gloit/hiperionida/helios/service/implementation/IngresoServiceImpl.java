package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.IngresoMapper;
import gloit.hiperionida.helios.mapper.creation.IngresoCreation;
import gloit.hiperionida.helios.mapper.creation.IngresoCreation;
import gloit.hiperionida.helios.model.IngresoModel;
import gloit.hiperionida.helios.model.CiudadModel;
import gloit.hiperionida.helios.model.IngresoModel;
import gloit.hiperionida.helios.repository.CiudadDAO;
import gloit.hiperionida.helios.repository.IngresoDAO;
import gloit.hiperionida.helios.service.IngresoService;
import gloit.hiperionida.helios.service.IngresoService;
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
public class IngresoServiceImpl implements IngresoService {
    private final IngresoDAO ingresoDAO;
    private final IngresoMapper ingresoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public IngresoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Ingreso con id: {}.", id);
        IngresoModel IngresoModel = ingresoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Ingreso con id: " + id + "."));
        log.info("Se encontró una entidad Ingreso con id: " + id + ".");
        return IngresoModel;
    }

    @Override
    public IngresoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Ingreso con id: {}, incluidas las eliminadas.", id);
        IngresoModel IngresoModel = ingresoDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Ingreso con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Ingreso con id: " + id + ", incluidas las eliminadas.");
        return IngresoModel;
    }

    @Override
    public List<IngresoModel> buscarTodas() {
        log.info("Buscando todas las entidades Ingreso.");
        List<IngresoModel> listado = ingresoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Ingreso.");
        return listado;
    }

    @Override
    public List<IngresoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Ingreso, incluidas las eliminadas.");
        List<IngresoModel> listado = ingresoDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Ingreso, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<IngresoModel> buscarTodasPorOrdenPorPagina(String Ingreso, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Ingreso, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, Ingreso);
        Slice<IngresoModel> slice = ingresoDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(Ingreso.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Ingreso.");
        return slice;
    }

    @Override
    public Slice<IngresoModel> buscarTodasPorOrdenPorPaginaConEliminadas(String Ingreso, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Ingreso, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, Ingreso);
        Slice<IngresoModel> slice = ingresoDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(Ingreso.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Ingreso, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = ingresoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Ingreso.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = ingresoDAO.count();
        log.info("Existen {} entidades Ingreso, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public IngresoModel crear(IngresoModel model) {
        log.info("Insertando la entidad IngresoModel: {}.",  model);
        IngresoModel ingresoModel = ingresoDAO.save(model);
        if (model.getId() == null) {
            ingresoModel.setCreada(Helper.getNow(""));
            ingresoModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad IngresoModel.");
        } else {
            ingresoModel.setModificada(Helper.getNow(""));
            ingresoModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad IngresoModel.");
        }
        return ingresoDAO.save(ingresoModel);
    }

    @Override
    public IngresoModel guardar(IngresoCreation creation) {
        log.info("Insertando la entidad IngresoCreation: {}.",  creation);
        IngresoModel ingresoModel = ingresoDAO.save(ingresoMapper.toEntity(creation));
        if (creation.getId() == null) {
            ingresoModel.setCreada(Helper.getNow(""));
            ingresoModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad IngresoCreation.");
        } else {
            ingresoModel.setModificada(Helper.getNow(""));
            ingresoModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad IngresoCreation.");
        }
        return ingresoDAO.save(ingresoModel);
    }

    @Override
    public IngresoModel eliminar(Long id) {
        log.info("Eliminando la entidad Ingreso con id: {}.", id);
        IngresoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Ingreso con id: " + id + ", fue eliminada correctamente.");
        return ingresoDAO.save(objeto);
    }

    @Override
    public IngresoModel reciclar(Long id) {
        log.info("Reciclando la entidad Ingreso con id: {}.", id);
        IngresoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Ingreso con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Ingreso con id: " + id + ", fue reciclada correctamente.");
        return ingresoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Ingreso con id: {}.", id);
        IngresoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Ingreso con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        ingresoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
