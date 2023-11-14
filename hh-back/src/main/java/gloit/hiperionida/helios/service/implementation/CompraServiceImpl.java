package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.CompraMapper;
import gloit.hiperionida.helios.mapper.creation.CompraCreation;
import gloit.hiperionida.helios.model.CompraModel;
import gloit.hiperionida.helios.repository.CompraDAO;
import gloit.hiperionida.helios.service.CompraService;
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
public class CompraServiceImpl implements CompraService {
    private final CompraDAO compraDAO;
    private final CompraMapper compraMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public CompraModel buscarPorId(Long id) {
        log.info("Buscando la entidad Compra con id: {}.", id);
        CompraModel compraModel = compraDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Compra con id: " + id + "."));
        log.info("Se encontro una entidad Compra con id: " + id + ".");
        return compraModel;
    }

    @Override
    public CompraModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Compra con id: {}, incluidas las eliminadas.", id);
        CompraModel compraModel = compraDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Compra con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Compra con id: " + id + ", incluidas las eliminadas.");
        return compraModel;
    }

    @Override
    public List<CompraModel> buscarTodas() {
        log.info("Buscando todas las entidades Compra.");
        List<CompraModel> listado = compraDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Compra.");
        return listado;
    }

    @Override
    public List<CompraModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Compra, incluidas las eliminadas.");
        List<CompraModel> listado = compraDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Compra, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<CompraModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Compra, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<CompraModel> slice = compraDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Compra.");
        return slice;
    }

    @Override
    public Slice<CompraModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Compra, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<CompraModel> slice = compraDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Compra, incluidas las eliminadas.");
        return slice;
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
            compraModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            compraModel.setModificada(Helper.getNow(""));
            compraModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return compraDAO.save(compraModel);
    }

    @Override
    public CompraModel eliminar(Long id) {
        log.info("Eliminando la entidad Compra con id: {}.", id);
        CompraModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Compra con id: " + id + ", fue eliminada correctamente.");
        return compraDAO.save(objeto);
    }

    @Override
    public CompraModel reciclar(Long id) {
        log.info("Reciclando la entidad Compra con id: {}.", id);
        CompraModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Compra con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Compra con id: " + id + ", fue reciclada correctamente.");
        return compraDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Compra con id: {}.", id);
        CompraModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Compra con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        compraDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
