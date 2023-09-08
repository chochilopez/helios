package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.CompraMapper;
import gloit.hiperionida.helios.mapper.creation.CompraCreation;
import gloit.hiperionida.helios.model.CompraModel;
import gloit.hiperionida.helios.repository.CompraDAO;
import gloit.hiperionida.helios.service.CompraService;
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
public class CompraServiceImpl implements CompraService {
    private final CompraDAO compraDAO;
    private final CompraMapper compraMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public CompraModel buscarPorId(Long id) {
        log.info("Buscando la entidad Compra con id: {}.", id);
        CompraModel compraModel = compraDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Compra con id: " + id + "."));
        String mensaje = "Se encontro una entidad Compra.";
        log.info(mensaje);
        return compraModel;
    }

    @Override
    public CompraModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Compra con id: {}, incluidas las eliminadas.", id);
        CompraModel compraModel = compraDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Compra con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Compra con id: " + id + ".");
        return compraModel;
    }

    @Override
    public List<CompraModel> buscarTodas() {
        log.info("Buscando todas las entidades Compra.");
        List<CompraModel> listado = compraDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Compra.");
        return listado;
    }

    @Override
    public List<CompraModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Compra, incluidas las eliminadas.");
        List<CompraModel> listado = compraDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Compra, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = compraDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Compra.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = compraDAO.count();
        log.info("Existen {} entidades Compra, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public CompraModel guardar(CompraCreation creation) {
        log.info("Insertando la entidad Compra: {}.",  creation);
        CompraModel compraModel = compraDAO.save(compraMapper.toEntity(creation));
        if (creation.getId() == null) {
            compraModel.setCreada(Helper.getNow(""));
            compraModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            compraModel.setModificada(Helper.getNow(""));
            compraModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return compraDAO.save(compraModel);
    }

    @Override
    public CompraModel eliminar(Long id) {
        log.info("Eliminando la entidad Compra con id: {}.", id);
        CompraModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Compra con id: " + id + ", fue eliminada correctamente.");
        return compraDAO.save(objeto);
    }

    @Override
    public CompraModel reciclar(Long id) {
        log.info("Reciclando la entidad Compra con id: {}.", id);
        CompraModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Compra con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Compra con id: " + id + ", fue reciclada correctamente.");
        return compraDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Compra con id: {}.", id);
        CompraModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Compra con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        compraDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
