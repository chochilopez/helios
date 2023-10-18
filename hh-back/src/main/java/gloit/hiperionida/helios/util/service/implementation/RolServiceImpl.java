package gloit.hiperionida.helios.util.service.implementation;

import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.model.enums.RolEnum;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.exception.ObjectoNoEliminadoException;
import gloit.hiperionida.helios.util.mapper.RolMapper;
import gloit.hiperionida.helios.util.mapper.creation.RolCreation;
import gloit.hiperionida.helios.util.model.RolModel;
import gloit.hiperionida.helios.util.repository.RolDAO;
import gloit.hiperionida.helios.util.service.RolService;
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
public class RolServiceImpl implements RolService {
    private final RolDAO rolDAO;
    private final RolMapper rolMapper;

    @Override
    public RolModel buscarPorRol(String nombre) {
        log.info("Buscando todas las entidades Rol con nombre: {}.", nombre);
        RolModel rol = rolDAO.findByRolAndEliminadaIsNull(RolEnum.valueOf(nombre)).orElseThrow(() -> new DatosInexistentesException("No se encontro la entidad Rol con nombre: " + nombre + "."));
        String mensaje = "Se encontro una entidad Rol con nombre: " + nombre + ".";
        log.info(mensaje);
        return rol;
    }

    @Override
    public RolModel buscarPorRolConEliminadas(String nombre) {
        log.info("Buscando todas las entidades Rol con nombre: {}, incluidas las eliminadas.", nombre);
        RolModel rol = rolDAO.findByRol(RolEnum.valueOf(nombre)).orElseThrow(() -> new DatosInexistentesException("No se encontro la entidad Rol con nombre: " + nombre + ", incluidas las eliminadas."));
        String mensaje = "Se encontro una entidad Rol con nombre: " + nombre + ", incluidas las eliminadas.";
        log.info(mensaje);
        return rol;
    }

    @Override
    public RolModel buscarPorId(Long id) {
        log.info("Buscando la entidad Rol con id: {}.", id);
        RolModel rolModel = rolDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad con id " + id + "."));
        String mensaje = "Se encontro una entidad Rol.";
        log.info(mensaje);
        return rolModel;
    }

    @Override
    public RolModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Rol con id: {}, incluidas las eliminadas.", id);
        RolModel rolModel = rolDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Rol con id: " + id + ".");
        return rolModel;
    }

    @Override
    public List<RolModel> buscarTodas() {
        log.info("Buscando todas las entidades Rol.");
        List<RolModel> listado = rolDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Rol.");
        return listado;
    }

    @Override
    public List<RolModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Rol, incluidas las eliminadas.");
        List<RolModel> listado = rolDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Rol, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<RolModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Rol, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<RolModel> slice = rolDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Rol.");
        return slice;
    }

    @Override
    public Slice<RolModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Rol, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<RolModel> slice = rolDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Rol, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = rolDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Rol.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = rolDAO.count();
        log.info("Existen {} entidades Rol, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public RolModel guardar(RolCreation creation) {
        log.info("Insertando la entidad Rol: {}.",  creation);
        RolModel rolModel = rolDAO.save(rolMapper.toEntity(creation));
        if (creation.getId() == null) {
            rolModel.setCreada(Helper.getNow(""));
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            rolModel.setModificada(Helper.getNow(""));
            log.info("Se persistio correctamente la entidad.");
        }
        return rolDAO.save(rolModel);
    }

    @Override
    public RolModel eliminar(Long id) {
        log.info("Eliminando la entidad Rol con id: {}.", id);
        RolModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        log.info("La entidad Rol con id: " + id + ", fue eliminada correctamente.");
        return rolDAO.save(objeto);
    }

    @Override
    public RolModel reciclar(Long id) {
        log.info("Reciclando la entidad Rol con id: {}.", id);
        RolModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Rol con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Rol con id: " + id + ", fue reciclada correctamente.");
        return rolDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Rol con id: {}.", id);
        RolModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Rol con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        rolDAO.delete(objeto);
        log.info("La entidad fue destruida y el rol " + objeto + " fue eliminado correctamente.");
    }
}
