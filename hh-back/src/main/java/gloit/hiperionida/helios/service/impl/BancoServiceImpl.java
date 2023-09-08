package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.BancoMapper;
import gloit.hiperionida.helios.mapper.creation.BancoCreation;
import gloit.hiperionida.helios.model.BancoModel;
import gloit.hiperionida.helios.repository.BancoDAO;
import gloit.hiperionida.helios.service.BancoService;
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
public class BancoServiceImpl implements BancoService {
    private final BancoDAO bancoDAO;
    private final BancoMapper bancoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public BancoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Banco con id: {}.", id);
        BancoModel bancoModel = bancoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Banco con id: " + id + "."));
        String mensaje = "Se encontro una entidad Banco.";
        log.info(mensaje);
        return bancoModel;
    }

    @Override
    public BancoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Banco con id: {}, incluidas las eliminadas.", id);
        BancoModel bancoModel = bancoDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Banco con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Banco con id: " + id + ".");
        return bancoModel;
    }

    @Override
    public List<BancoModel> buscarTodas() {
        log.info("Buscando todas las entidades Banco.");
        List<BancoModel> listado = bancoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Banco.");
        return listado;
    }

    @Override
    public List<BancoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Banco, incluidas las eliminadas.");
        List<BancoModel> listado = bancoDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Banco, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = bancoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Banco.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = bancoDAO.count();
        log.info("Existen {} entidades Banco, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public BancoModel guardar(BancoCreation creation) {
        log.info("Insertando la entidad Banco: {}.",  creation);
        BancoModel bancoModel = bancoDAO.save(bancoMapper.toEntity(creation));
        if (creation.getId() == null) {
            bancoModel.setCreada(Helper.getNow(""));
            bancoModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            bancoModel.setModificada(Helper.getNow(""));
            bancoModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return bancoDAO.save(bancoModel);
    }

    @Override
    public BancoModel eliminar(Long id) {
        log.info("Eliminando la entidad Banco con id: {}.", id);
        BancoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Banco con id: " + id + ", fue eliminada correctamente.");
        return bancoDAO.save(objeto);
    }

    @Override
    public BancoModel reciclar(Long id) {
        log.info("Reciclando la entidad Banco con id: {}.", id);
        BancoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Banco con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Banco con id: " + id + ", fue reciclada correctamente.");
        return bancoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Banco con id: {}.", id);
        BancoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Banco con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        bancoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
