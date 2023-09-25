package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.AdelantoMapper;
import gloit.hiperionida.helios.mapper.creation.AdelantoCreation;
import gloit.hiperionida.helios.mapper.dto.AdelantoDTO;
import gloit.hiperionida.helios.model.AdelantoModel;
import gloit.hiperionida.helios.service.implementation.AdelantoServiceImpl;
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
@RequestMapping(value = "/api/adelanto")
@RequiredArgsConstructor
@RestController
@Slf4j
public class AdelantoController extends AbsBaseController {
    private final AdelantoServiceImpl adelantoService;
    private final AdelantoMapper adelantoMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el adelanto. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<AdelantoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        AdelantoModel objeto = adelantoService.buscarPorId(id);
        return new ResponseEntity<>(adelantoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<AdelantoDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        AdelantoModel objeto = adelantoService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(adelantoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<AdelantoDTO>> buscarTodas() {
        List<AdelantoModel> listado = adelantoService.buscarTodas();
        ArrayList<AdelantoDTO> adelantos = new ArrayList<>();
        for (AdelantoModel adelanto:listado) {
            adelantos.add(adelantoMapper.toDto(adelanto));
        }
        return new ResponseEntity<>(adelantos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<AdelantoDTO>> buscarTodasConEliminadas() {
        List<AdelantoModel> listado = adelantoService.buscarTodasConEliminadas();
        ArrayList<AdelantoDTO> adelantos = new ArrayList<>();
        for (AdelantoModel adelanto:listado) {
            adelantos.add(adelantoMapper.toDto(adelanto));
        }
        return new ResponseEntity<>(adelantos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<AdelantoDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<AdelantoModel> listado = adelantoService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<AdelantoDTO> adelantos = new ArrayList<>();
        for (AdelantoModel adelanto:listado) {
            adelantos.add(adelantoMapper.toDto(adelanto));
        }
        Slice slice = new SliceImpl(adelantos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<AdelantoDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<AdelantoModel> listado = adelantoService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<AdelantoDTO> adelantos = new ArrayList<>();
        for (AdelantoModel adelanto:listado) {
            adelantos.add(adelantoMapper.toDto(adelanto));
        }
        Slice slice = new SliceImpl(adelantos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= adelantoService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= adelantoService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<AdelantoDTO> guardar(@Valid @RequestBody AdelantoCreation adelantoCreation) {
        AdelantoModel objeto = adelantoService.guardar(adelantoCreation);
        return new ResponseEntity<>(adelantoMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<AdelantoDTO> borrar(@PathVariable(name = "id") Long id) {
        AdelantoModel objeto = adelantoService.eliminar(id);
        return new ResponseEntity<>(adelantoMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<AdelantoDTO> reciclar(@PathVariable(name = "id") Long id) {
        AdelantoModel objeto = adelantoService.reciclar(id);
        return new ResponseEntity<>(adelantoMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        adelantoService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

