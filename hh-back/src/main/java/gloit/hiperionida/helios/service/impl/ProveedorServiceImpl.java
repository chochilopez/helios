package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.ProveedorMapper;
import gloit.hiperionida.helios.mapper.creation.ProveedorCreation;
import gloit.hiperionida.helios.model.ProveedorModel;
import gloit.hiperionida.helios.repository.ProveedorDAO;
import gloit.hiperionida.helios.service.ProveedorService;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.CustomDataNotFoundException;
import gloit.hiperionida.helios.util.exception.CustomObjectNotDeletedException;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {
    private final ProveedorDAO proveedorDAO;
    private final ProveedorMapper proveedorMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public ProveedorModel buscarPorId(Long id) {
        log.info("Buscando la entidad Proveedor con id: {}.", id);
        ProveedorModel proveedorModel = proveedorDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Proveedor con id: " + id + "."));
        String mensaje = "Se encontro una entidad Proveedor.";
        log.info(mensaje);
        return proveedorModel;
    }

    @Override
    public ProveedorModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Proveedor con id: {}, incluidas las eliminadas.", id);
        ProveedorModel proveedorModel = proveedorDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Proveedor con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Proveedor con id: " + id + ".");
        return proveedorModel;
    }

    @Override
    public List<ProveedorModel> buscarTodas() {
        log.info("Buscando todas las entidades Proveedor.");
        List<ProveedorModel> listado = proveedorDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Proveedor.");
        return listado;
    }

    @Override
    public List<ProveedorModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Proveedor, incluidas las eliminadas.");
        List<ProveedorModel> listado = proveedorDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Proveedor, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = proveedorDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Proveedor.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = proveedorDAO.count();
        log.info("Existen {} entidades Proveedor, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ProveedorModel guardar(ProveedorCreation creation) {
        log.info("Insertando la entidad Proveedor: {}.",  creation);
        ProveedorModel proveedorModel = proveedorDAO.save(proveedorMapper.toEntity(creation));
        if (creation.getId() == null) {
            proveedorModel.setCreada(Helper.getNow(""));
            proveedorModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            proveedorModel.setModificada(Helper.getNow(""));
            proveedorModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return proveedorDAO.save(proveedorModel);
    }

    @Override
    public ProveedorModel eliminar(Long id) {
        log.info("Eliminando la entidad Proveedor con id: {}.", id);
        ProveedorModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Proveedor con id: " + id + ", fue eliminada correctamente.");
        return proveedorDAO.save(objeto);
    }

    @Override
    public ProveedorModel reciclar(Long id) {
        log.info("Reciclando la entidad Proveedor con id: {}.", id);
        ProveedorModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Proveedor con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Proveedor con id: " + id + ", fue reciclada correctamente.");
        return proveedorDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Proveedor con id: {}.", id);
        ProveedorModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Proveedor con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        proveedorDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
