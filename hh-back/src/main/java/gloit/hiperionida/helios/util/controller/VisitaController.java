package gloit.hiperionida.helios.util.controller;

import gloit.hiperionida.helios.util.mapper.LogueoMapper;
import gloit.hiperionida.helios.util.mapper.creation.LogueoCreation;
import gloit.hiperionida.helios.util.mapper.dto.LogueoDTO;
import gloit.hiperionida.helios.util.model.LogueoModel;
import gloit.hiperionida.helios.util.service.implementation.LogueoServiceImpl;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.dto.ErrorDTO;
import gloit.hiperionida.helios.util.mapper.dto.PaginadoDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/visita")
@RequiredArgsConstructor
@RestController
@Slf4j
public class VisitaController extends AbsBaseController {
    private final LogueoServiceImpl visitaService;
    private final LogueoMapper logueoMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el visita. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<LogueoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        LogueoModel objeto = visitaService.buscarPorId(id);
        return new ResponseEntity<>(logueoMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<LogueoDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        LogueoModel objeto = visitaService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(logueoMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<LogueoDTO>> buscarTodas() {
        List<LogueoModel> listado = visitaService.buscarTodas();
        ArrayList<LogueoDTO> visitas = new ArrayList<>();
        for (LogueoModel visita:listado) {
            visitas.add(logueoMapper.toDto(visita));
        }
        return new ResponseEntity<>(visitas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<LogueoDTO>> buscarTodasConEliminadas() {
        List<LogueoModel> listado = visitaService.buscarTodasConEliminadas();
        ArrayList<LogueoDTO> visitas = new ArrayList<>();
        for (LogueoModel visita:listado) {
            visitas.add(logueoMapper.toDto(visita));
        }
        return new ResponseEntity<>(visitas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<LogueoDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<LogueoModel> listado = visitaService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<LogueoDTO> visitas = new ArrayList<>();
        for (LogueoModel visita:listado) {
            visitas.add(logueoMapper.toDto(visita));
        }
        Slice slice = new SliceImpl(visitas, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<LogueoDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<LogueoModel> listado = visitaService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<LogueoDTO> visitas = new ArrayList<>();
        for (LogueoModel visita:listado) {
            visitas.add(logueoMapper.toDto(visita));
        }
        Slice slice = new SliceImpl(visitas, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= visitaService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= visitaService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<LogueoDTO> guardar(@Valid @RequestBody LogueoCreation logueoCreation) {
        LogueoModel objeto = visitaService.guardar(logueoCreation);
        return new ResponseEntity<>(logueoMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<LogueoDTO> borrar(@PathVariable(name = "id") Long id) {
        LogueoModel objeto = visitaService.eliminar(id);
        return new ResponseEntity<>(logueoMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<LogueoDTO> reciclar(@PathVariable(name = "id") Long id) {
        LogueoModel objeto = visitaService.reciclar(id);
        return new ResponseEntity<>(logueoMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        visitaService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

