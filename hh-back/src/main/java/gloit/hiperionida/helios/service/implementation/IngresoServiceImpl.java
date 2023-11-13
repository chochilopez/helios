package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.IngresoMapper;
import gloit.hiperionida.helios.mapper.creation.IngresoCreation;
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
    private final IngresoDAO IngresoDAO;
    private final IngresoMapper IngresoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public IngresoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Ingreso con id: {}.", id);
        IngresoModel IngresoModel = IngresoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Ingreso con id: " + id + "."));
        log.info("Se encontro una entidad Ingreso con id: " + id + ".");
        return IngresoModel;
    }

    @Override
    public IngresoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Ingreso con id: {}, incluidas las eliminadas.", id);
        IngresoModel IngresoModel = IngresoDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Ingreso con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Ingreso con id: " + id + ", incluidas las eliminadas.");
        return IngresoModel;
    }

    @Override
    public List<IngresoModel> buscarTodas() {
        log.info("Buscando todas las entidades Ingreso.");
        List<IngresoModel> listado = IngresoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Ingreso.");
        return listado;
    }

    @Override
    public List<IngresoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Ingreso, incluidas las eliminadas.");
        List<IngresoModel> listado = IngresoDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Ingreso, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<IngresoModel> buscarTodasPorOrdenPorPagina(String Ingreso, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Ingreso, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, Ingreso);
        Slice<IngresoModel> slice = IngresoDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(Ingreso.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Ingreso.");
        return slice;
    }

    @Override
    public Slice<IngresoModel> buscarTodasPorOrdenPorPaginaConEliminadas(String Ingreso, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Ingreso, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, Ingreso);
        Slice<IngresoModel> slice = IngresoDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(Ingreso.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Ingreso, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = IngresoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Ingreso.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = IngresoDAO.count();
        log.info("Existen {} entidades Ingreso, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public IngresoModel guardar(IngresoCreation creation) {
        log.info("Insertando la entidad Ingreso: {}.",  creation);
        IngresoModel IngresoModel = IngresoDAO.save(IngresoMapper.toEntity(creation));
        if (creation.getId() == null) {
            IngresoModel.setCreada(Helper.getNow(""));
            IngresoModel.setCreador_id(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            IngresoModel.setModificada(Helper.getNow(""));
            IngresoModel.setModificador_id(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return IngresoDAO.save(IngresoModel);
    }

    @Override
    public IngresoModel eliminar(Long id) {
        log.info("Eliminando la entidad Ingreso con id: {}.", id);
        IngresoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador_id(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Ingreso con id: " + id + ", fue eliminada correctamente.");
        return IngresoDAO.save(objeto);
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
        objeto.setEliminador_id(null);
        log.info("La entidad Ingreso con id: " + id + ", fue reciclada correctamente.");
        return IngresoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Ingreso con id: {}.", id);
        IngresoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Ingreso con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        IngresoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
