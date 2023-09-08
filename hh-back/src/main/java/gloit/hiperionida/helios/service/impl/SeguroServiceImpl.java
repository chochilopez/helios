package gloit.hiperionida.helios.service.impl;

import gloit.hiperionida.helios.mapper.SeguroMapper;
import gloit.hiperionida.helios.mapper.creation.SeguroCreation;
import gloit.hiperionida.helios.model.SeguroModel;
import gloit.hiperionida.helios.repository.SeguroDAO;
import gloit.hiperionida.helios.service.SeguroService;
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
public class SeguroServiceImpl implements SeguroService {
    private final SeguroDAO seguroDAO;
    private final SeguroMapper seguroMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public SeguroModel buscarPorId(Long id) {
        log.info("Buscando la entidad Seguro con id: {}.", id);
        SeguroModel seguroModel = seguroDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Seguro con id: " + id + "."));
        String mensaje = "Se encontro una entidad Seguro.";
        log.info(mensaje);
        return seguroModel;
    }

    @Override
    public SeguroModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Seguro con id: {}, incluidas las eliminadas.", id);
        SeguroModel seguroModel = seguroDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Seguro con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Seguro con id: " + id + ".");
        return seguroModel;
    }

    @Override
    public List<SeguroModel> buscarTodas() {
        log.info("Buscando todas las entidades Seguro.");
        List<SeguroModel> listado = seguroDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Seguro.");
        return listado;
    }

    @Override
    public List<SeguroModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Seguro, incluidas las eliminadas.");
        List<SeguroModel> listado = seguroDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Seguro, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = seguroDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Seguro.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = seguroDAO.count();
        log.info("Existen {} entidades Seguro, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public SeguroModel guardar(SeguroCreation creation) {
        log.info("Insertando la entidad Seguro: {}.",  creation);
        SeguroModel seguroModel = seguroDAO.save(seguroMapper.toEntity(creation));
        if (creation.getId() == null) {
            seguroModel.setCreada(Helper.getNow(""));
            seguroModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            seguroModel.setModificada(Helper.getNow(""));
            seguroModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persistio correctamente la entidad.");
        }
        return seguroDAO.save(seguroModel);
    }

    @Override
    public SeguroModel eliminar(Long id) {
        log.info("Eliminando la entidad Seguro con id: {}.", id);
        SeguroModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Seguro con id: " + id + ", fue eliminada correctamente.");
        return seguroDAO.save(objeto);
    }

    @Override
    public SeguroModel reciclar(Long id) {
        log.info("Reciclando la entidad Seguro con id: {}.", id);
        SeguroModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Seguro con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Seguro con id: " + id + ", fue reciclada correctamente.");
        return seguroDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Seguro con id: {}.", id);
        SeguroModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Seguro con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        seguroDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
