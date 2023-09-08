package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.FacturaMapper;
import gloit.hiperionida.helios.mapper.creation.FacturaCreation;
import gloit.hiperionida.helios.model.FacturaModel;
import gloit.hiperionida.helios.repository.FacturaDAO;
import gloit.hiperionida.helios.service.FacturaService;
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
public class FacturaServiceImpl implements FacturaService {
    private final FacturaDAO facturaDAO;
    private final FacturaMapper facturaMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public FacturaModel buscarPorId(Long id) {
        log.info("Buscando la entidad Factura con id: {}.", id);
        FacturaModel facturaModel = facturaDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Factura con id: " + id + "."));
        String mensaje = "Se encontro una entidad Factura.";
        log.info(mensaje);
        return facturaModel;
    }

    @Override
    public FacturaModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Factura con id: {}, incluidas las eliminadas.", id);
        FacturaModel facturaModel = facturaDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Factura con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Factura con id: " + id + ".");
        return facturaModel;
    }

    @Override
    public List<FacturaModel> buscarTodas() {
        log.info("Buscando todas las entidades Factura.");
        List<FacturaModel> listado = facturaDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Factura.");
        return listado;
    }

    @Override
    public List<FacturaModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Factura, incluidas las eliminadas.");
        List<FacturaModel> listado = facturaDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Factura, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = facturaDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Factura.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = facturaDAO.count();
        log.info("Existen {} entidades Factura, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public FacturaModel guardar(FacturaCreation creation) {
        log.info("Insertando la entidad Factura: {}.",  creation);
        FacturaModel facturaModel = facturaDAO.save(facturaMapper.toEntity(creation));
        if (creation.getId() == null) {
            facturaModel.setCreada(Helper.getNow(""));
            facturaModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            facturaModel.setModificada(Helper.getNow(""));
            facturaModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return facturaDAO.save(facturaModel);
    }

    @Override
    public FacturaModel eliminar(Long id) {
        log.info("Eliminando la entidad Factura con id: {}.", id);
        FacturaModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Factura con id: " + id + ", fue eliminada correctamente.");
        return facturaDAO.save(objeto);
    }

    @Override
    public FacturaModel reciclar(Long id) {
        log.info("Reciclando la entidad Factura con id: {}.", id);
        FacturaModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Factura con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Factura con id: " + id + ", fue reciclada correctamente.");
        return facturaDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Factura con id: {}.", id);
        FacturaModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Factura con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        facturaDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
