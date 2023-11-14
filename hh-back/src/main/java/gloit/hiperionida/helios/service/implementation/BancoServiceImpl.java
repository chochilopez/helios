package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.BancoMapper;
import gloit.hiperionida.helios.mapper.creation.BancoCreation;
import gloit.hiperionida.helios.model.BancoModel;
import gloit.hiperionida.helios.repository.BancoDAO;
import gloit.hiperionida.helios.service.BancoService;
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
public class BancoServiceImpl implements BancoService {
    private final BancoDAO bancoDAO;
    private final BancoMapper bancoMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public BancoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Banco con id: {}.", id);
        BancoModel bancoModel = bancoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Banco con id: " + id + "."));
        log.info("Se encontro una entidad Banco con id: " + id + ".");
        return bancoModel;
    }

    @Override
    public BancoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Banco con id: {}, incluidas las eliminadas.", id);
        BancoModel bancoModel = bancoDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Banco con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Banco con id: " + id + ", incluidas las eliminadas.");
        return bancoModel;
    }

    @Override
    public List<BancoModel> buscarTodas() {
        log.info("Buscando todas las entidades Banco.");
        List<BancoModel> listado = bancoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Banco.");
        return listado;
    }

    @Override
    public List<BancoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Banco, incluidas las eliminadas.");
        List<BancoModel> listado = bancoDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Banco, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<BancoModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Banco, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<BancoModel> slice = bancoDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Banco.");
        return slice;
    }

    @Override
    public Slice<BancoModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Banco, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<BancoModel> slice = bancoDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Banco, incluidas las eliminadas.");
        return slice;
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
            bancoModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            bancoModel.setModificada(Helper.getNow(""));
            bancoModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return bancoDAO.save(bancoModel);
    }

    @Override
    public BancoModel eliminar(Long id) {
        log.info("Eliminando la entidad Banco con id: {}.", id);
        BancoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Banco con id: " + id + ", fue eliminada correctamente.");
        return bancoDAO.save(objeto);
    }

    @Override
    public BancoModel reciclar(Long id) {
        log.info("Reciclando la entidad Banco con id: {}.", id);
        BancoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Banco con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Banco con id: " + id + ", fue reciclada correctamente.");
        return bancoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Banco con id: {}.", id);
        BancoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Banco con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        bancoDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
