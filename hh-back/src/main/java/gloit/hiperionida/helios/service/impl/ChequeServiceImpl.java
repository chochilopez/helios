package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.ChequeMapper;
import gloit.hiperionida.helios.mapper.creation.ChequeCreation;
import gloit.hiperionida.helios.model.ChequeModel;
import gloit.hiperionida.helios.repository.ChequeDAO;
import gloit.hiperionida.helios.service.ChequeService;
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
public class ChequeServiceImpl implements ChequeService {
    private final ChequeDAO chequeDAO;
    private final ChequeMapper chequeMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public ChequeModel buscarPorId(Long id) {
        log.info("Buscando la entidad Cheque con id: {}.", id);
        ChequeModel chequeModel = chequeDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Cheque con id: " + id + "."));
        String mensaje = "Se encontro una entidad Cheque.";
        log.info(mensaje);
        return chequeModel;
    }

    @Override
    public ChequeModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Cheque con id: {}, incluidas las eliminadas.", id);
        ChequeModel chequeModel = chequeDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Cheque con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Cheque con id: " + id + ".");
        return chequeModel;
    }

    @Override
    public List<ChequeModel> buscarTodas() {
        log.info("Buscando todas las entidades Cheque.");
        List<ChequeModel> listado = chequeDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Cheque.");
        return listado;
    }

    @Override
    public List<ChequeModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Cheque, incluidas las eliminadas.");
        List<ChequeModel> listado = chequeDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Cheque, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = chequeDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Cheque.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = chequeDAO.count();
        log.info("Existen {} entidades Cheque, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ChequeModel guardar(ChequeCreation creation) {
        log.info("Insertando la entidad Cheque: {}.",  creation);
        ChequeModel chequeModel = chequeDAO.save(chequeMapper.toEntity(creation));
        if (creation.getId() == null) {
            chequeModel.setCreada(Helper.getNow(""));
            chequeModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            chequeModel.setModificada(Helper.getNow(""));
            chequeModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return chequeDAO.save(chequeModel);
    }

    @Override
    public ChequeModel eliminar(Long id) {
        log.info("Eliminando la entidad Cheque con id: {}.", id);
        ChequeModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Cheque con id: " + id + ", fue eliminada correctamente.");
        return chequeDAO.save(objeto);
    }

    @Override
    public ChequeModel reciclar(Long id) {
        log.info("Reciclando la entidad Cheque con id: {}.", id);
        ChequeModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Cheque con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Cheque con id: " + id + ", fue reciclada correctamente.");
        return chequeDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Cheque con id: {}.", id);
        ChequeModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Cheque con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        chequeDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
