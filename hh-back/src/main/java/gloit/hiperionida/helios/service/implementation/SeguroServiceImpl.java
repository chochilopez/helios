package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.SeguroMapper;
import gloit.hiperionida.helios.mapper.creation.SeguroCreation;
import gloit.hiperionida.helios.model.AcopladoModel;
import gloit.hiperionida.helios.model.SeguroModel;
import gloit.hiperionida.helios.repository.SeguroDAO;
import gloit.hiperionida.helios.service.SeguroService;
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
public class SeguroServiceImpl implements SeguroService {
    private final SeguroDAO seguroDAO;
    private final SeguroMapper seguroMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public SeguroModel buscarPorAseguradoraId(Long id) {
        log.info("Buscando la entidad Seguro con id de aseguradora: {}.", id);
        SeguroModel seguroModel = seguroDAO.findByAseguradoraIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Seguro con id de aseguradora: " + id + "."));
        log.info("Se encontro una entidad Seguro con id de aseguradora: " + id + ".");
        return seguroModel;
    }

    @Override
    public SeguroModel buscarPorAseguradoraIdConEliminadas(Long id) {
        log.info("Buscando la entidad Seguro con id de aseguradora: {}, incluidas las eliminadas.", id);
        SeguroModel seguroModel = seguroDAO.findByAseguradoraId(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Seguro con id de aseguradora: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Seguro con id de aseguradora: " + id + ", incluidas las eliminadas.");
        return seguroModel;
    }

    @Override
    public SeguroModel buscarPorVencimientoId(Long id) {
        log.info("Buscando la entidad Seguro con id de vencimiento: {}.", id);
        SeguroModel seguroModel = seguroDAO.findByVencimientoIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Seguro con id de vencimiento: " + id + "."));
        log.info("Se encontro una entidad Seguro con id de vencimiento: " + id + ".");
        return seguroModel;
    }

    @Override
    public SeguroModel buscarPorVencimientoIdConEliminadas(Long id) {
        log.info("Buscando la entidad Seguro con id de vencimiento: {}, incluidas las eliminadas.", id);
        SeguroModel seguroModel = seguroDAO.findByVencimientoId(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Seguro con id de vencimiento: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Seguro con id de vencimiento: " + id + ", incluidas las eliminadas.");
        return seguroModel;
    }

    @Override
    public List<SeguroModel> buscarTodasPorNotas(String notas) {
        log.info("Buscando todas las entidades Acoplado con notas: {}.", notas);
        List<SeguroModel> listado = seguroDAO.findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Acoplado con notas: " + notas + ".");
        return listado;
    }

    @Override
    public List<SeguroModel> buscarTodasPorNotasConEliminadas(String notas) {
        log.info("Buscando todas las entidades Acoplado con notas: {}, incluidas las eliminadas.", notas);
        List<SeguroModel> listado = seguroDAO.findAllByNotasContainingIgnoreCase(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Acoplado con notas: " + notas + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public SeguroModel buscarPorId(Long id) {
        log.info("Buscando la entidad Seguro con id: {}.", id);
        SeguroModel seguroModel = seguroDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Seguro con id: " + id + "."));
        log.info("Se encontro una entidad Seguro con id: " + id + ".");
        return seguroModel;
    }

    @Override
    public SeguroModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Seguro con id: {}, incluidas las eliminadas.", id);
        SeguroModel seguroModel = seguroDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontro la entidad Seguro con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Seguro con id: " + id + ", incluidas las eliminadas.");
        return seguroModel;
    }

    @Override
    public List<SeguroModel> buscarTodas() {
        log.info("Buscando todas las entidades Seguro.");
        List<SeguroModel> listado = seguroDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro.");
        return listado;
    }

    @Override
    public List<SeguroModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Seguro, incluidas las eliminadas.");
        List<SeguroModel> listado = seguroDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<SeguroModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Seguro, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<SeguroModel> slice = seguroDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro.");
        return slice;
    }

    @Override
    public Slice<SeguroModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Seguro, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<SeguroModel> slice = seguroDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro, incluidas las eliminadas.");
        return slice;
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
            seguroModel.setCreador_id(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            seguroModel.setModificada(Helper.getNow(""));
            seguroModel.setModificador_id(usuarioService.obtenerUsuario().getId());
            log.info("Se persistio correctamente la entidad.");
        }
        return seguroDAO.save(seguroModel);
    }

    @Override
    public SeguroModel eliminar(Long id) {
        log.info("Eliminando la entidad Seguro con id: {}.", id);
        SeguroModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador_id(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Seguro con id: " + id + ", fue eliminada correctamente.");
        return seguroDAO.save(objeto);
    }

    @Override
    public SeguroModel reciclar(Long id) {
        log.info("Reciclando la entidad Seguro con id: {}.", id);
        SeguroModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Seguro con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador_id(null);
        log.info("La entidad Seguro con id: " + id + ", fue reciclada correctamente.");
        return seguroDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Seguro con id: {}.", id);
        SeguroModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Seguro con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        seguroDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
