package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.NeumaticoMapper;
import gloit.hiperionida.helios.mapper.creation.NeumaticoCreation;
import gloit.hiperionida.helios.model.NeumaticoModel;
import gloit.hiperionida.helios.repository.NeumaticoDAO;
import gloit.hiperionida.helios.service.NeumaticoService;
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
public class NeumaticoServiceImpl implements NeumaticoService {
    private final NeumaticoDAO neumaticoDAO;
    private final NeumaticoMapper neumaticoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public NeumaticoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Neumatico con id: {}.", id);
        NeumaticoModel neumaticoModel = neumaticoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Neumatico con id: " + id + "."));
        String mensaje = "Se encontro una entidad Neumatico.";
        log.info(mensaje);
        return neumaticoModel;
    }

    @Override
    public NeumaticoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Neumatico con id: {}, incluidas las eliminadas.", id);
        NeumaticoModel neumaticoModel = neumaticoDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Neumatico con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Neumatico con id: " + id + ".");
        return neumaticoModel;
    }

    @Override
    public List<NeumaticoModel> buscarTodas() {
        log.info("Buscando todas las entidades Neumatico.");
        List<NeumaticoModel> listado = neumaticoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Neumatico.");
        return listado;
    }

    @Override
    public List<NeumaticoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Neumatico, incluidas las eliminadas.");
        List<NeumaticoModel> listado = neumaticoDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Neumatico, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = neumaticoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Neumatico.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = neumaticoDAO.count();
        log.info("Existen {} entidades Neumatico, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public NeumaticoModel guardar(NeumaticoCreation creation) {
        log.info("Insertando la entidad Neumatico: {}.",  creation);
        NeumaticoModel neumaticoModel = neumaticoDAO.save(neumaticoMapper.toEntity(creation));
        if (creation.getId() == null) {
            neumaticoModel.setCreada(Helper.getNow(""));
            neumaticoModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            neumaticoModel.setModificada(Helper.getNow(""));
            neumaticoModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return neumaticoDAO.save(neumaticoModel);
    }

    @Override
    public NeumaticoModel eliminar(Long id) {
        log.info("Eliminando la entidad Neumatico con id: {}.", id);
        NeumaticoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Neumatico con id: " + id + ", fue eliminada correctamente.");
        return neumaticoDAO.save(objeto);
    }

    @Override
    public NeumaticoModel reciclar(Long id) {
        log.info("Reciclando la entidad Neumatico con id: {}.", id);
        NeumaticoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Neumatico con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Neumatico con id: " + id + ", fue reciclada correctamente.");
        return neumaticoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Neumatico con id: {}.", id);
        NeumaticoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Neumatico con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        neumaticoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
