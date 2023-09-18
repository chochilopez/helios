package gloit.hiperionida.helios.util.service.implementation;

import gloit.hiperionida.helios.util.model.enums.TipoArchivoEnum;
import gloit.hiperionida.helios.util.exception.CustomDataNotFoundException;
import gloit.hiperionida.helios.util.exception.CustomObjectNotDeletedException;
import gloit.hiperionida.helios.util.mapper.ArchivoMapper;
import gloit.hiperionida.helios.util.mapper.creation.ArchivoCreation;
import gloit.hiperionida.helios.util.model.ArchivoModel;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.repository.ArchivoDAO;
import gloit.hiperionida.helios.util.service.ArchivoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArchivoServiceImpl implements ArchivoService {
    private final ArchivoDAO archivoDAO;
    private final ArchivoMapper archivoMapper;
    private final UsuarioServiceImpl usuarioService;
    @Value("${neso.app.resourcePath}")
    private String resourcePath;

    @Override
    public ArchivoModel guardarArchivo(byte[] file, String nombre, String tipo, String descripcion, String tamanio) throws IOException {
        log.info("Guardando el archivo " + nombre + " en Archivo.");
        String filepath = "";
        switch (TipoArchivoEnum.valueOf(tipo)) {
            case AUDIO -> filepath = filepath + "/media/audio/";
            case IMAGEN -> filepath = filepath + "/media/image/";
            case VIDEO -> filepath = filepath + "/media/video/";
            case DOCUMENTO -> filepath = filepath + "/media/pdf/";
            default -> {
            }
        }
        Path path = Paths.get(resourcePath + filepath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            log.info("The directory: {} was created.", path);
        } else {
            log.info("The directory: {} was already created.", path);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        filepath = filepath + Helper.localDateTimeToString(localDateTime, "yyyy") + "/";
        path = Paths.get(resourcePath + filepath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            log.info("The directory: {} was created.", path);
        } else {
            log.info("The directory: {} was already created.", path);
        }
        filepath = filepath + Helper.localDateTimeToString(localDateTime, "M") + "/" ;
        path = Paths.get(resourcePath + filepath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            log.info("The directory: {} was created.", path);
        } else {
            log.info("The directory: {} was already created.", path);
        }
        filepath = filepath + Helper.localDateTimeToString(localDateTime, "d") + "/" ;
        path = Paths.get(resourcePath + filepath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            log.info("The directory: {} was created.", path);
        } else {
            log.info("The directory: {} was already created.", path);
        }
        Files.write(Paths.get(resourcePath + filepath + nombre), file);
        ArchivoCreation archivoCreation = new ArchivoCreation();
        archivoCreation.setTipo(tipo);
        archivoCreation.setNombre(nombre);
        archivoCreation.setTamanio(tamanio);
        archivoCreation.setDescripcion(descripcion);
        archivoCreation.setPath(filepath);

        return this.guardar(archivoCreation);
    }

    @Override
    public ArchivoModel buscarPorId(Long id) {
        log.info("Buscando la entidad Archivo con id: {}.", id);
        ArchivoModel archivoModel = archivoDAO.findByIdAndEliminadaIsNull(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Archivo con id: " + id + "."));
        log.info("Se encontro una entidad Archivo con id: {}.", id);
        return archivoModel;
    }

    @Override
    public ArchivoModel buscarPorIdConEliminadas(Long id) {
        log.info("Buscando la entidad Archivo con id: {}, incluidas las eliminadas.", id);
        ArchivoModel archivoModel = archivoDAO.findById(id).orElseThrow(()-> new CustomDataNotFoundException("No se encontro la entidad Archivo con id: " + id +", incluidas las eliminadas."));
        log.info("Se encontro una entidad Archivo con id: {} , incluidas las eliminadas.", id);
        return archivoModel;
    }

    @Override
    public List<ArchivoModel> buscarTodas() {
        log.info("Buscando todas las entidades Archivo.");
        List<ArchivoModel> listado = archivoDAO.findAllByEliminadaIsNull();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Archivo.");
        return listado;
    }

    @Override
    public List<ArchivoModel> buscarTodasConEliminadas() {
        log.info("Buscando todas las entidades Archivo, incluidas las eliminadas.");
        List<ArchivoModel> listado = archivoDAO.findAll();
        if (listado.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Archivo, incluidas las eliminadas.");
        return listado;
    }

    @Override
    public Slice<ArchivoModel> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Archivo, por la pagina {} con {} elementos, ordenadas por el campo {} {}.", pagina, elementos, campo, direccion);
        Slice<ArchivoModel> slice = archivoDAO.findAllByEliminadaIsNull(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Archivo.");
        return slice;
    }

    @Override
    public Slice<ArchivoModel> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos) {
        log.info("Buscando todas las entidades Archivo, por la pagina {} con {} elementos, ordenadas por el campo {} {}, incluidas las eliminadas.", pagina, elementos, campo, direccion);
        Slice<ArchivoModel> slice = archivoDAO.findAll(PageRequest.of(pagina, elementos, Sort.by(direccion.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, campo)));
        if (slice.isEmpty())
            throw new CustomDataNotFoundException("No se encontraron entidades Archivo, incluidas las eliminadas.");
        return slice;
    }

    @Override
    public Long contarTodas() {
        Long cantidad = archivoDAO.countAllByEliminadaIsNull();
        log.info("Existen {} entidades Archivo.", cantidad);
        return cantidad;
    }

    @Override
    public Long contarTodasConEliminadas() {
        Long cantidad = archivoDAO.count();
        log.info("Existen {} entidades Archivo, incluidas las eliminadas.", cantidad);
        return cantidad;
    }

    @Override
    public ArchivoModel guardar(ArchivoCreation creation) {
        log.info("Insertando la entidad Archivo: {}.",  creation);
        ArchivoModel archivoModel = archivoDAO.save(archivoMapper.toEntity(creation));
        if (creation.getId() == null) {
            archivoModel.setCreada(Helper.getNow(""));
            archivoModel.setCreador(usuarioService.obtenerUsuario());
            log.info("Se persisitio correctamente la nueva entidad.");
        } else {
            archivoModel.setModificada(Helper.getNow(""));
            archivoModel.setModificador(usuarioService.obtenerUsuario());
            log.info("Se persisitio correctamente la entidad.");
        }
        return archivoDAO.save(archivoModel);
    }

    @Override
    public ArchivoModel eliminar(Long id) {
        log.info("Eliminando la entidad Archivo con id: {}.", id);
        ArchivoModel objeto = this.buscarPorId(id);
        objeto.setEliminada(Helper.getNow(""));
        objeto.setEliminador(usuarioService.obtenerUsuario());
        log.info("La entidad Archivo con id: {}, fue eliminada correctamente.", id);
        return archivoDAO.save(objeto);
    }

    @Override
    public ArchivoModel reciclar(Long id) {
        log.info("Reciclando la entidad Archivo con id: {}.", id);
        ArchivoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Archivo con id: {}, no se encuentra eliminada, por lo tanto no es necesario reciclarla.", id);
            throw new CustomObjectNotDeletedException("No se puede reciclar la entidad.");
        }
        objeto.setEliminada(null);
        objeto.setEliminador(null);
        log.info("La entidad Archivo con id: {}, fue reciclada correctamente.", id);
        return archivoDAO.save(objeto);
    }

    @Override
    public void destruir(Long id) throws IOException {
        log.info("Destruyendo la entidad Archivo con id: {}.", id);
        ArchivoModel objeto = this.buscarPorIdConEliminadas(id);
        if (objeto.getEliminada() == null) {
            log.warn("La entidad Archivo con id: {}, no se encuentra eliminada, por lo tanto no puede ser destruida.", id);
            throw new CustomObjectNotDeletedException("No se puede destruir la entidad.");
        }
        Path fileToDeletePath = Paths.get(objeto.getPath() + "/" + objeto.getNombre());
        Files.delete(fileToDeletePath);
        archivoDAO.delete(objeto);
        log.info("La entidad fue destruida y el archivo {} fue eliminado correctamente.", id);
    }
}
