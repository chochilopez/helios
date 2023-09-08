package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.PlanCuentaMapper;
import gloit.hiperionida.helios.mapper.creation.PlanCuentaCreation;
import gloit.hiperionida.helios.model.PlanCuentaModel;
import gloit.hiperionida.helios.repository.PlanCuentaDAO;
import gloit.hiperionida.helios.service.PlanCuentaService;
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
public class PlanCuentaServiceImpl implements PlanCuentaService {
    private final PlanCuentaDAO planCuentaDAO;
    private final PlanCuentaMapper planCuentaMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public PlanCuentaModel buscarPorId(Long id) {
        log.info("Buscando la entidad PlanCuenta con id: {}.", id);
        PlanCuentaModel planCuentaModel = planCuentaDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad PlanCuenta con id: " + id + "."));
        String mensaje = "Se encontro una entidad PlanCuenta.";
        log.info(mensaje);
        return planCuentaModel;
    }

    @Override
    public PlanCuentaModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad PlanCuenta con id: {}, incluidas las eliminadas.", id);
        PlanCuentaModel planCuentaModel = planCuentaDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad PlanCuenta con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad PlanCuenta con id: " + id + ".");
        return planCuentaModel;
    }

    @Override
    public List<PlanCuentaModel> buscarTodas() {
        log.info("Buscando todas las entidades PlanCuenta.");
        List<PlanCuentaModel> listado = planCuentaDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades PlanCuenta.");
        return listado;
    }

    @Override
    public List<PlanCuentaModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades PlanCuenta, incluidas las eliminadas.");
        List<PlanCuentaModel> listado = planCuentaDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades PlanCuenta, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = planCuentaDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades PlanCuenta.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = planCuentaDAO.count();
        log.info("Existen {} entidades PlanCuenta, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public PlanCuentaModel guardar(PlanCuentaCreation creation) {
        log.info("Insertando la entidad PlanCuenta: {}.",  creation);
        PlanCuentaModel planCuentaModel = planCuentaDAO.save(planCuentaMapper.toEntity(creation));
        if (creation.getId() == null) {
            planCuentaModel.setCreada(Helper.getNow(""));
            planCuentaModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            planCuentaModel.setModificada(Helper.getNow(""));
            planCuentaModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return planCuentaDAO.save(planCuentaModel);
    }

    @Override
    public PlanCuentaModel eliminar(Long id) {
        log.info("Eliminando la entidad PlanCuenta con id: {}.", id);
        PlanCuentaModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad PlanCuenta con id: " + id + ", fue eliminada correctamente.");
        return planCuentaDAO.save(objeto);
    }

    @Override
    public PlanCuentaModel reciclar(Long id) {
        log.info("Reciclando la entidad PlanCuenta con id: {}.", id);
        PlanCuentaModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad PlanCuenta con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad PlanCuenta con id: " + id + ", fue reciclada correctamente.");
        return planCuentaDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad PlanCuenta con id: {}.", id);
        PlanCuentaModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad PlanCuenta con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        planCuentaDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
