package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.SeguroMapper;
import gloit.hiperionida.helios.mapper.creation.SeguroCreation;
import gloit.hiperionida.helios.model.SeguroModel;
import gloit.hiperionida.helios.repository.SeguroDAO;
import gloit.hiperionida.helios.service.SeguroService;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.exception.ObjectoNoEliminadoException;
import gloit.hiperionida.helios.util.exception.ParametroInvalidoException;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeguroServiceImpl implements SeguroService {
    private final SeguroDAO seguroDAO;
    private final SeguroMapper seguroMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public List<SeguroModel> buscarTodasPorAcopladoId(Long id) {
        log.info("Buscando todas las entidades Seguro con acoplado id: {}.", id);
        List<SeguroModel> listado = seguroDAO.findAllByAcopladoIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro con acoplado id: " + id + ".");
        return listado;
    }

    @Override
    public List<SeguroModel> buscarTodasPorAcopladoIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Seguro con acoplado id: {}, incluidas las eliminadas.", id);
        List<SeguroModel> listado = seguroDAO.findAllByAcopladoId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro con acoplado id: " + id + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<SeguroModel> buscarTodasPorAseguradoraId(Long id) {
        log.info("Buscando todas las entidades Seguro con aseguradora id: {}.", id);
        List<SeguroModel> listado = seguroDAO.findAllByAseguradoraIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro con aseguradora id: " + id + ".");
        return listado;
    }

    @Override
    public List<SeguroModel> buscarTodasPorAseguradoraIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Seguro con aseguradora id: {}, incluidas las eliminadas.", id);
        List<SeguroModel> listado = seguroDAO.findAllByAseguradoraId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron Seguro licencia con aseguradora id: " + id + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<SeguroModel> buscarTodasPorCamionId(Long id) {
        log.info("Buscando todas las entidades Seguro con camión id: {}.", id);
        List<SeguroModel> listado = seguroDAO.findAllByCamionIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro con camión id: " + id + ".");
        return listado;
    }

    @Override
    public List<SeguroModel> buscarTodasPorCamionIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Seguro con camión id: {}, incluidas las eliminadas.", id);
        List<SeguroModel> listado = seguroDAO.findAllByCamionId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro con camión id: " + id + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<SeguroModel> buscarTodasPorVencimientoId(Long id) {
        log.info("Buscando todas las entidades Seguro con vencimiento id: {}.", id);
        List<SeguroModel> listado = seguroDAO.findAllByVencimientoIdAndEliminadaIsNull(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro con vencimiento id: " + id + ".");
        return listado;
    }

    @Override
    public List<SeguroModel> buscarTodasPorVencimientoIdConEliminadas(Long id) {
        log.info("Buscando todas las entidades Seguro con vencimiento id: {}, incluidas las eliminadas.", id);
        List<SeguroModel> listado = seguroDAO.findAllByVencimientoId(id);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro con vencimiento id: " + id + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<SeguroModel> buscarTodasPorNotas(String notas) {
        log.info("Buscando todas las entidades Seguro con notas: {}.", notas);
        List<SeguroModel> listado = seguroDAO.findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro con notas: " + notas + ".");
        return listado;
    }

    @Override
    public List<SeguroModel> buscarTodasPorNotasConEliminadas(String notas) {
        log.info("Buscando todas las entidades Seguro con notas: {}, incluidas las eliminadas.", notas);
        List<SeguroModel> listado = seguroDAO.findAllByNotasContainingIgnoreCase(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro con notas: " + notas + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<SeguroModel> buscarTodasPorVencimientoBetween(String inicio, String fin) {
        log.info("Buscando todas las entidades Seguro entre las fechas: {} y {}.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son válidas.");
        List<SeguroModel> listado = seguroDAO.findAllByVencimientoBetweenAndEliminadaIsNull( fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro entre las fechas: " + inicio + " y " + fin + ".");
        return listado;
    }

    @Override
    public List<SeguroModel> buscarTodasPorVencimientoBetweenConEliminadas(String inicio, String fin) {
        log.info("Buscando todas las entidades Seguro entre las fechas: {} y {}, con eliminadas.", inicio, fin);
        LocalDateTime fInicio = Helper.stringToLocalDateTime(inicio, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime fFin = Helper.stringToLocalDateTime(fin, "yyyy-MM-dd HH:mm:ss");
        if (fInicio == null || fFin == null)
            throw new ParametroInvalidoException("Alguna de las fechas ingresadas no son válidas.");
        List<SeguroModel> listado = seguroDAO.findAllByVencimientoBetween(fInicio, fFin);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Seguro entre las fechas: " + inicio + " y " + fin + ", con eliminadas.");
        return listado;
    }

    @Override
    public SeguroModel buscarPorId(Long id) {
        log.info("Buscando la entidad Seguro con id: {}.", id);
        SeguroModel seguroModel = seguroDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Seguro con id: " + id + "."));
        log.info("Se encontró una entidad Seguro con id: " + id + ".");
        return seguroModel;
    }

    @Override
    public SeguroModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Seguro con id: {}, incluidas las eliminadas.", id);
        SeguroModel seguroModel = seguroDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Seguro con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Seguro con id: " + id + ", incluidas las eliminadas.");
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
    public SeguroModel crear(SeguroModel model) {
        log.info("Insertando la entidad SeguroModel: {}.",  model);
        SeguroModel seguroModel = seguroDAO.save(model);
        if (model.getId() == null) {
            seguroModel.setCreada(Helper.getNow(""));
            seguroModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad SeguroModel.");
        } else {
            seguroModel.setModificada(Helper.getNow(""));
            seguroModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad SeguroModel.");
        }
        return seguroDAO.save(seguroModel);
    }

    @Override
    public SeguroModel guardar(SeguroCreation creation) {
        log.info("Insertando la entidad SeguroCreation: {}.",  creation);
        SeguroModel seguroModel = seguroDAO.save(seguroMapper.toEntity(creation));
        if (creation.getId() == null) {
            seguroModel.setCreada(Helper.getNow(""));
            seguroModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad SeguroCreation.");
        } else {
            seguroModel.setModificada(Helper.getNow(""));
            seguroModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad SeguroCreation.");
        }
        return seguroDAO.save(seguroModel);
    }

    @Override
    public SeguroModel eliminar(Long id) {
        log.info("Eliminando la entidad Seguro con id: {}.", id);
        SeguroModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
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
        objeto.setEliminadorId(null);
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
