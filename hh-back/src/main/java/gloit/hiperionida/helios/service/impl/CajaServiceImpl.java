package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.CajaMapper;
import gloit.hiperionida.helios.mapper.creation.CajaCreation;
import gloit.hiperionida.helios.model.CajaModel;
import gloit.hiperionida.helios.repository.CajaDAO;
import gloit.hiperionida.helios.service.CajaService;
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
public class CajaServiceImpl implements CajaService {
    private final CajaDAO cajaDAO;
    private final CajaMapper cajaMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public CajaModel buscarPorId(Long id) {
        log.info("Buscando la entidad Caja con id: {}.", id);
        CajaModel cajaModel = cajaDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Caja con id: " + id + "."));
        String mensaje = "Se encontro una entidad Caja.";
        log.info(mensaje);
        return cajaModel;
    }

    @Override
    public CajaModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Caja con id: {}, incluidas las eliminadas.", id);
        CajaModel cajaModel = cajaDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Caja con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Caja con id: " + id + ".");
        return cajaModel;
    }

    @Override
    public List<CajaModel> buscarTodas() {
        log.info("Buscando todas las entidades Caja.");
        List<CajaModel> listado = cajaDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Caja.");
        return listado;
    }

    @Override
    public List<CajaModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Caja, incluidas las eliminadas.");
        List<CajaModel> listado = cajaDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Caja, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = cajaDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Caja.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = cajaDAO.count();
        log.info("Existen {} entidades Caja, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public CajaModel guardar(CajaCreation creation) {
        log.info("Insertando la entidad Caja: {}.",  creation);
        CajaModel cajaModel = cajaDAO.save(cajaMapper.toEntity(creation));
        if (creation.getId() == null) {
            cajaModel.setCreada(Helper.getNow(""));
            cajaModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            cajaModel.setModificada(Helper.getNow(""));
            cajaModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return cajaDAO.save(cajaModel);
    }

    @Override
    public CajaModel eliminar(Long id) {
        log.info("Eliminando la entidad Caja con id: {}.", id);
        CajaModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Caja con id: " + id + ", fue eliminada correctamente.");
        return cajaDAO.save(objeto);
    }

    @Override
    public CajaModel reciclar(Long id) {
        log.info("Reciclando la entidad Caja con id: {}.", id);
        CajaModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Caja con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Caja con id: " + id + ", fue reciclada correctamente.");
        return cajaDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Caja con id: {}.", id);
        CajaModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Caja con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        cajaDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
