package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.AcopladoMapper;
import gloit.hiperionida.helios.mapper.creation.AcopladoCreation;
import gloit.hiperionida.helios.model.AcopladoModel;
import gloit.hiperionida.helios.repository.AcopladoDAO;
import gloit.hiperionida.helios.service.AcopladoService;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.CustomDataNotFoundException;
import gloit.hiperionida.helios.util.exception.CustomObjectNotDeletedException;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AcopladoServiceImpl implements AcopladoService {
    private final AcopladoDAO acopladoDAO;
    private final AcopladoMapper acopladoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public AcopladoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Acoplado con id: {}.", id);
        AcopladoModel acopladoModel = acopladoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Acoplado con id: " + id + "."));
        log.info("Se encontro una entidad Acoplado con id: {}.", id);
        return acopladoModel;
    }

    @Override
    public AcopladoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Acoplado con id: {}, incluidas las eliminadas.", id);
        AcopladoModel acopladoModel = acopladoDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Acoplado con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Acoplado con id: {}, incluidas las eliminadas.", id);
        return acopladoModel;
    }

    @Override
    public List<AcopladoModel> buscarTodas() {
        log.info("Buscando todas las entidades Acoplado.");
        List<AcopladoModel> listado = acopladoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Acoplado.");
        return listado;
    }

    @Override
    public List<AcopladoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Acoplado, incluidas las eliminadas.");
        List<AcopladoModel> listado = acopladoDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Acoplado, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = acopladoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Acoplado.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = acopladoDAO.count();
        log.info("Existen {} entidades Acoplado, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public AcopladoModel guardar(AcopladoCreation creation) {
        log.info("Insertando la entidad Acoplado: {}.",  creation);
        AcopladoModel acopladoModel = acopladoDAO.save(acopladoMapper.toEntity(creation));
        if (creation.getId() == null) {
            acopladoModel.setCreada(Helper.getNow(""));
            acopladoModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            acopladoModel.setModificada(Helper.getNow(""));
            acopladoModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return acopladoDAO.save(acopladoModel);
    }

    @Override
    public AcopladoModel eliminar(Long id) {
        log.info("Eliminando la entidad Acoplado con id: {}.", id);
        AcopladoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Acoplado con id: {}, fue eliminada correctamente.", id);
        return acopladoDAO.save(objeto);
    }

    @Override
    public AcopladoModel reciclar(Long id) {
        log.info("Reciclando la entidad Acoplado con id: {}.", id);
        AcopladoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Acoplado con id: {}, no se encuentra eliminada, por lo tanto no es necesario reciclarla.", id);
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Acoplado con id: {}, fue reciclada correctamente.", id);
        return acopladoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Acoplado con id: {}.", id);
        AcopladoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Acoplado con id: {}, no se encuentra eliminada, por lo tanto no puede ser destruida.", id);
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        acopladoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
