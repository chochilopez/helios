package gloit.hiperionida.helios.service.implementation;

import gloit.hiperionida.helios.mapper.CamionMapper;
import gloit.hiperionida.helios.mapper.creation.CamionCreation;
import gloit.hiperionida.helios.mapper.creation.CamionCreation;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.repository.CamionDAO;
import gloit.hiperionida.helios.service.CamionService;
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
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CamionServiceImpl implements CamionService {
    private final CamionDAO camionDAO;
    private final CamionMapper camionMapper;
    private final UsuarioServiceImpl usuarioService;

    @Override
    public List<CamionModel> buscarTodasPorAnio(Integer anio) {
        log.info("Buscando todas las entidades Camion por año: {}.", anio);
        List<CamionModel> listado = camionDAO.findAllByAnioAndEliminadaIsNull(anio);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion por año: " + anio + ".");
        return listado;
    }

    @Override
    public List<CamionModel> buscarTodasPorAnioConEliminadas(Integer anio) {
        log.info("Buscando todas las entidades Camion por año: {}, incluidas las eliminadas.", anio);
        List<CamionModel> listado = camionDAO.findAllByAnio(anio);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion por año: " + anio + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<CamionModel> buscarTodasPorMarcaModelo(String marcaModelo) {
        log.info("Buscando todas las entidades Camion con marca/modelo: {}.", marcaModelo);
        List<CamionModel> listado = camionDAO.findAllByMarcaModeloContainingIgnoreCaseAndEliminadaIsNull(marcaModelo);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion con marca/modelo: " + marcaModelo + ".");
        return listado;
    }

    @Override
    public List<CamionModel> buscarTodasPorMarcaModeloConEliminadas(String marcaModelo) {
        log.info("Buscando todas las entidades Camion con marca/modelo: {}, incluidas las eliminadas.", marcaModelo);
        List<CamionModel> listado = camionDAO.findAllByMarcaModeloContainingIgnoreCase(marcaModelo);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion con marca/modelo: " + marcaModelo + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<CamionModel> buscarTodasPorNumeroChasis(String numeroChasis) {
        log.info("Buscando todas las entidades Camion con numero de chasis: {}.", numeroChasis);
        List<CamionModel> listado = camionDAO.findAllByNumeroChasisContainingIgnoreCaseAndEliminadaIsNull(numeroChasis);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion con numero de chasis: " + numeroChasis + ".");
        return listado;
    }

    @Override
    public List<CamionModel> buscarTodasPorNumeroChasisConEliminadas(String numeroChasis) {
        log.info("Buscando todas las entidades Camion con numero de chasis: {}, incluidas las eliminadas.", numeroChasis);
        List<CamionModel> listado = camionDAO.findAllByNumeroChasisContainingIgnoreCase(numeroChasis);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion con numero de chasis: " + numeroChasis + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<CamionModel> buscarTodasPorNumeroMotor(String numeroMotor) {
        log.info("Buscando todas las entidades Camion con numero de motor: {}.", numeroMotor);
        List<CamionModel> listado = camionDAO.findAllByNumeroMotorContainingIgnoreCaseAndEliminadaIsNull(numeroMotor);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion con numero de motor: " + numeroMotor + ".");
        return listado;
    }

    @Override
    public List<CamionModel> buscarTodasPorNumeroMotorConEliminadas(String numeroMotor) {
        log.info("Buscando todas las entidades Camion con numero de motor: {}, incluidas las eliminadas.", numeroMotor);
        List<CamionModel> listado = camionDAO.findAllByNumeroMotorContainingIgnoreCase(numeroMotor);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion con numero de motor: " + numeroMotor + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<CamionModel> buscarTodasPorNotas(String notas) {
        log.info("Buscando todas las entidades Camion con notas: {}.", notas);
        List<CamionModel> listado = camionDAO.findAllByNotasContainingIgnoreCaseAndEliminadaIsNull(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion con notas: " + notas + ".");
        return listado;
    }

    @Override
    public List<CamionModel> buscarTodasPorNotasConEliminadas(String notas) {
        log.info("Buscando todas las entidades Camion con notas: {}, incluidas las eliminadas.", notas);
        List<CamionModel> listado = camionDAO.findAllByNotasContainingIgnoreCase(notas);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion con notas: " + notas + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public List<CamionModel> buscarTodasPorPatente(String patente) {
        log.info("Buscando todas las entidades Camion con patente: {}.", patente);
        List<CamionModel> listado = camionDAO.findAllByPatenteContainingIgnoreCaseAndEliminadaIsNull(patente);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion con patente: " + patente + ".");
        return listado;
    }

    @Override
    public List<CamionModel> buscarTodasPorPatenteConEliminadas(String patente) {
        log.info("Buscando todas las entidades Camion con patente: {}, incluidas las eliminadas.", patente);
        List<CamionModel> listado = camionDAO.findAllByPatenteContainingIgnoreCase(patente);
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion con patente: " + patente + ", incluidas las eliminadas.");
        return listado;
    }

    @Override
    public CamionModel buscarPorId(Long id) {
        log.info("Buscando la entidad Camion con id: {}.", id);
        CamionModel camionModel = camionDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Camion con id: " + id + "."));
        log.info("Se encontró una entidad Camion con id: " + id + ".");
        return camionModel;
    }

    @Override
    public CamionModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Camion con id: {}, incluidas las eliminadas.", id);
        CamionModel camionModel = camionDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad Camion con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontró una entidad Camion con id: " + id + ", incluidas las eliminadas.");
        return camionModel;
    }

    @Override
    public List<CamionModel> buscarTodas() {
        log.info("Buscando todas las entidades Camion.");
        List<CamionModel> listado = camionDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion.");
        log.info("Se encontraron {} entidades Camion.", listado.size());
        return listado;
    }

    @Override
    public List<CamionModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Camion, incluidas las eliminadas.");
        List<CamionModel> listado = camionDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion, incluidas las eliminadas.");
        log.info("Se encontraron {} entidades Camion, incluidas las eliminadas.", listado.size());
        return listado;
    }

    @Override
    public Slice<CamionModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Camion, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<CamionModel> slice = camionDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion.");
        return slice;
    }

    @Override
    public Slice<CamionModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Camion, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<CamionModel> slice = camionDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Camion, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = camionDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Camion.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = camionDAO.count();
        log.info("Existen {} entidades Camion, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public CamionModel crear(CamionModel model) {
        log.info("Insertando la entidad CamionModel: {}.",  model);
        CamionModel camionModel = camionDAO.save(model);
        if (model.getId() == null) {
            camionModel.setCreada(Helper.getNow(""));
            camionModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad CamionModel.");
        } else {
            camionModel.setModificada(Helper.getNow(""));
            camionModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad CamionModel.");
        }
        return camionDAO.save(camionModel);
    }

    @Override
    public CamionModel guardar(CamionCreation creation) {
        log.info("Insertando la entidad CamionCreation: {}.",  creation);
        CamionModel camionModel = camionDAO.save(camionMapper.toEntity(creation));
        if (creation.getId() == null) {
            camionModel.setCreada(Helper.getNow(""));
            camionModel.setCreadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la nueva entidad CamionCreation.");
        } else {
            camionModel.setModificada(Helper.getNow(""));
            camionModel.setModificadorId(usuarioService.obtenerUsuario().getId());
            log.info("Se persisitio correctamente la entidad CamionCreation.");
        }
        return camionDAO.save(camionModel);
    }

    @Override
    public CamionModel eliminar(Long id) {
        log.info("Eliminando la entidad Camion con id: {}.", id);
        CamionModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminadorId(usuarioService.obtenerUsuario().getId());
        log.info("La entidad Camion con id: " + id + ", fue eliminada correctamente.");
        return camionDAO.save(objeto);
    }

    @Override
    public CamionModel reciclar(Long id) {
        log.info("Reciclando la entidad Camion con id: {}.", id);
        CamionModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Camion con id: " + id + ", no se encuentra eliminada, por lo tanto no es necesario reciclarla.");
            throw new ObjectoNoEliminadoException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminadorId(null);
        log.info("La entidad Camion con id: " + id + ", fue reciclada correctamente.");
        return camionDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Camion con id: {}.", id);
        CamionModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Camion con id: " + id + ", no se encuentra eliminada, por lo tanto no puede ser destruida.");
            throw new ObjectoNoEliminadoException("No se puede destruir la entidad.");
        }
        camionDAO.delete(objeto);
        log.info("La entidad fue destruida.");
    }
}
