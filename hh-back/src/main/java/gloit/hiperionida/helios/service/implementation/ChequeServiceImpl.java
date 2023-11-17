package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.ChequeMapper;
import gloit.hiperionida.helios.mapper.creation.ChequeCreation;
import gloit.hiperionida.helios.model.ChequeModel;
import gloit.hiperionida.helios.repository.ChequeDAO;
import gloit.hiperionida.helios.service.ChequeService;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.exception.ObjectoNoEliminadoException;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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
        ChequeModel chequeModel = chequeDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Cheque con id: " + id + "."));
        log.info("Se encontró una entidad Cheque con id: " + id + ".");
        return chequeModel;
    }

    @Override
    public ChequeModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Cheque con id: {}, incluidas las eliminadas.", id);
        ChequeModel chequeModel = chequeDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Cheque con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Cheque con id: " + id + ", incluidas las eliminadas.");
        return chequeModel;
    }

    @Override
    public List<ChequeModel> buscarTodas() {
        log.info("Buscando todas las entidades Cheque.");
        List<ChequeModel> listado = chequeDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cheque.");
        return listado;
    }

    @Override
    public List<ChequeModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Cheque, incluidas las eliminadas.");
        List<ChequeModel> listado = chequeDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cheque, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<ChequeModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Cheque, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<ChequeModel> slice = chequeDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cheque.");
        return slice;
    }

    @Override
    public Slice<ChequeModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Cheque, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<ChequeModel> slice = chequeDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Cheque, incluidas las eliminadas.");
        return slice;
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
            chequeModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            chequeModel.setModificada(Helper.getNow(""));
            chequeModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return chequeDAO.save(chequeModel);
    }

    @Override
    public ChequeModel eliminar(Long id) {
        log.info("Eliminando la entidad Cheque con id: {}.", id);
        ChequeModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Cheque con id: " + id + ", fue eliminada correctamente.");
        return chequeDAO.save(objeto);
    }

    @Override
    public ChequeModel reciclar(Long id) {
        log.info("Reciclando la entidad Cheque con id: {}.", id);
        ChequeModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Cheque con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Cheque con id: " + id + ", fue reciclada correctamente.");
        return chequeDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Cheque con id: {}.", id);
        ChequeModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Cheque con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        chequeDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
