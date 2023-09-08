package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.ClienteMapper;
import gloit.hiperionida.helios.mapper.creation.ClienteCreation;
import gloit.hiperionida.helios.model.ClienteModel;
import gloit.hiperionida.helios.repository.ClienteDAO;
import gloit.hiperionida.helios.service.ClienteService;
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
public class ClienteServiceImpl implements ClienteService {
    private final ClienteDAO clienteDAO;
    private final ClienteMapper clienteMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public ClienteModel buscarPorId(Long id) {
        log.info("Buscando la entidad Cliente con id: {}.", id);
        ClienteModel clienteModel = clienteDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Cliente con id: " + id + "."));
        String mensaje = "Se encontro una entidad Cliente.";
        log.info(mensaje);
        return clienteModel;
    }

    @Override
    public ClienteModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Cliente con id: {}, incluidas las eliminadas.", id);
        ClienteModel clienteModel = clienteDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Cliente con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Cliente con id: " + id + ".");
        return clienteModel;
    }

    @Override
    public List<ClienteModel> buscarTodas() {
        log.info("Buscando todas las entidades Cliente.");
        List<ClienteModel> listado = clienteDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Cliente.");
        return listado;
    }

    @Override
    public List<ClienteModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Cliente, incluidas las eliminadas.");
        List<ClienteModel> listado = clienteDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Cliente, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = clienteDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Cliente.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = clienteDAO.count();
        log.info("Existen {} entidades Cliente, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ClienteModel guardar(ClienteCreation creation) {
        log.info("Insertando la entidad Cliente: {}.",  creation);
        ClienteModel clienteModel = clienteDAO.save(clienteMapper.toEntity(creation));
        if (creation.getId() == null) {
            clienteModel.setCreada(Helper.getNow(""));
            clienteModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            clienteModel.setModificada(Helper.getNow(""));
            clienteModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return clienteDAO.save(clienteModel);
    }

    @Override
    public ClienteModel eliminar(Long id) {
        log.info("Eliminando la entidad Cliente con id: {}.", id);
        ClienteModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Cliente con id: " + id + ", fue eliminada correctamente.");
        return clienteDAO.save(objeto);
    }

    @Override
    public ClienteModel reciclar(Long id) {
        log.info("Reciclando la entidad Cliente con id: {}.", id);
        ClienteModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Cliente con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Cliente con id: " + id + ", fue reciclada correctamente.");
        return clienteDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Cliente con id: {}.", id);
        ClienteModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Cliente con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        clienteDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
