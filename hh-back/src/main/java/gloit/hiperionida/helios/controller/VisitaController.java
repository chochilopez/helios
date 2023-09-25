package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.VisitaMapper;
import gloit.hiperionida.helios.mapper.creation.VisitaCreation;
import gloit.hiperionida.helios.mapper.dto.VisitaDTO;
import gloit.hiperionida.helios.model.VisitaModel;
import gloit.hiperionida.helios.service.implementation.VisitaServiceImpl;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.controller.AbsBaseController;
import gloit.hiperionida.helios.util.exception.ErrorDTO;
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
    private final VisitaServiceImpl visitaService;
    private final VisitaMapper visitaMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el visita. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<VisitaDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        VisitaModel objeto = visitaService.buscarPorId(id);
        return new ResponseEntity<>(visitaMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<VisitaDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        VisitaModel objeto = visitaService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(visitaMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<VisitaDTO>> buscarTodas() {
        List<VisitaModel> listado = visitaService.buscarTodas();
        ArrayList<VisitaDTO> visitas = new ArrayList<>();
        for (VisitaModel visita:listado) {
            visitas.add(visitaMapper.toDto(visita));
        }
        return new ResponseEntity<>(visitas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<VisitaDTO>> buscarTodasConEliminadas() {
        List<VisitaModel> listado = visitaService.buscarTodasConEliminadas();
        ArrayList<VisitaDTO> visitas = new ArrayList<>();
        for (VisitaModel visita:listado) {
            visitas.add(visitaMapper.toDto(visita));
        }
        return new ResponseEntity<>(visitas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<VisitaDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<VisitaModel> listado = visitaService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<VisitaDTO> visitas = new ArrayList<>();
        for (VisitaModel visita:listado) {
            visitas.add(visitaMapper.toDto(visita));
        }
        Slice slice = new SliceImpl(visitas, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<VisitaDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<VisitaModel> listado = visitaService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<VisitaDTO> visitas = new ArrayList<>();
        for (VisitaModel visita:listado) {
            visitas.add(visitaMapper.toDto(visita));
        }
        Slice slice = new SliceImpl(visitas, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= visitaService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= visitaService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<VisitaDTO> guardar(@Valid @RequestBody VisitaCreation visitaCreation) {
        VisitaModel objeto = visitaService.guardar(visitaCreation);
        return new ResponseEntity<>(visitaMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<VisitaDTO> borrar(@PathVariable(name = "id") Long id) {
        VisitaModel objeto = visitaService.eliminar(id);
        return new ResponseEntity<>(visitaMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<VisitaDTO> reciclar(@PathVariable(name = "id") Long id) {
        VisitaModel objeto = visitaService.reciclar(id);
        return new ResponseEntity<>(visitaMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        visitaService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

