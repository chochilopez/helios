package gloit.hiperionida.helios.util.controller;

import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.ErrorDTO;
import gloit.hiperionida.helios.util.mapper.creation.ArchivoCreation;
import gloit.hiperionida.helios.util.mapper.dto.ArchivoDTO;
import gloit.hiperionida.helios.util.model.ArchivoModel;
import gloit.hiperionida.helios.util.service.implementation.ArchivoServiceImpl;
import gloit.hiperionida.helios.util.mapper.ArchivoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/archivo")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ArchivoController extends AbsBaseController {
    private final ArchivoServiceImpl archivoService;
    private final ArchivoMapper archivoMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el archivo. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), status);
    }

    @PutMapping(value = "/guardar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('AUTHORITY_EDITOR')")
    public ResponseEntity<ArchivoDTO> guardarArchivo(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("tipo") String tipo,
            @RequestParam("tamanio") String tamanio,
            @RequestParam("descripcion") String descripcion
    ) throws IOException {
        ArchivoModel objeto = archivoService.guardarArchivo(multipartFile.getBytes(), multipartFile.getOriginalFilename(), tipo, descripcion, tamanio);
        return new ResponseEntity<>(archivoMapper.toDto(objeto), Helper.httpHeaders("Imagen creada correctamente."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('CONTRIBUYENTE')")
    public ResponseEntity<ArchivoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        ArchivoModel objeto = archivoService.buscarPorId(id);
        return new ResponseEntity<>(archivoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('CAPATAZ')")
    public ResponseEntity<ArchivoDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        ArchivoModel objeto = archivoService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(archivoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('EMPLEADO')")
    public ResponseEntity<List<ArchivoDTO>> buscarTodas() {
        List<ArchivoModel> listado = archivoService.buscarTodas();
        ArrayList<ArchivoDTO> archivos = new ArrayList<>();
        for (ArchivoModel archivo:listado) {
            archivos.add(archivoMapper.toDto(archivo));
        }
        return new ResponseEntity<>(archivos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('CAPATAZ')")
    public ResponseEntity<List<ArchivoDTO>> buscarTodasConEliminadas() {
        List<ArchivoModel> listado = archivoService.buscarTodas();
        ArrayList<ArchivoDTO> archivos = new ArrayList<>();
        for (ArchivoModel archivo:listado) {
            archivos.add(archivoMapper.toDto(archivo));
        }
        return new ResponseEntity<>(archivos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('CONTRIBUYENTE')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= archivoService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('CAPATAZ')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= archivoService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('CAPATAZ')")
    public ResponseEntity<ArchivoDTO> guardar(@Valid @RequestBody ArchivoCreation archivoCreation) {
        ArchivoModel objeto = archivoService.guardar(archivoCreation);
        return new ResponseEntity<>(archivoMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CAPATAZ')")
    public ResponseEntity<ArchivoDTO> borrar(@PathVariable(name = "id") Long id) {
        ArchivoModel objeto = archivoService.eliminar(id);
        return new ResponseEntity<>(archivoMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('CAPATAZ')")
    public ResponseEntity<ArchivoDTO> reciclar(@PathVariable(name = "id") Long id) {
        ArchivoModel objeto = archivoService.reciclar(id);
        return new ResponseEntity<>(archivoMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('JEFE')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        archivoService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}
