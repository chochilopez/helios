package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.MovimientoMapper;
import gloit.hiperionida.helios.mapper.creation.MovimientoCreation;
import gloit.hiperionida.helios.mapper.dto.MovimientoDTO;
import gloit.hiperionida.helios.model.MovimientoModel;
import gloit.hiperionida.helios.service.implementation.MovimientoServiceImpl;
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
@RequestMapping(value = "/api/movimiento")
@RequiredArgsConstructor
@RestController
@Slf4j
public class MovimientoController extends AbsBaseController {
    private final MovimientoServiceImpl movimientoService;
    private final MovimientoMapper movimientoMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el movimiento. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<MovimientoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        MovimientoModel objeto = movimientoService.buscarPorId(id);
        return new ResponseEntity<>(movimientoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MovimientoDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        MovimientoModel objeto = movimientoService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(movimientoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<MovimientoDTO>> buscarTodas() {
        List<MovimientoModel> listado = movimientoService.buscarTodas();
        ArrayList<MovimientoDTO> movimientos = new ArrayList<>();
        for (MovimientoModel movimiento:listado) {
            movimientos.add(movimientoMapper.toDto(movimiento));
        }
        return new ResponseEntity<>(movimientos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<MovimientoDTO>> buscarTodasConEliminadas() {
        List<MovimientoModel> listado = movimientoService.buscarTodasConEliminadas();
        ArrayList<MovimientoDTO> movimientos = new ArrayList<>();
        for (MovimientoModel movimiento:listado) {
            movimientos.add(movimientoMapper.toDto(movimiento));
        }
        return new ResponseEntity<>(movimientos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<MovimientoDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<MovimientoModel> listado = movimientoService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<MovimientoDTO> movimientos = new ArrayList<>();
        for (MovimientoModel movimiento:listado) {
            movimientos.add(movimientoMapper.toDto(movimiento));
        }
        Slice slice = new SliceImpl(movimientos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<MovimientoDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<MovimientoModel> listado = movimientoService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<MovimientoDTO> movimientos = new ArrayList<>();
        for (MovimientoModel movimiento:listado) {
            movimientos.add(movimientoMapper.toDto(movimiento));
        }
        Slice slice = new SliceImpl(movimientos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= movimientoService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= movimientoService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<MovimientoDTO> guardar(@Valid @RequestBody MovimientoCreation movimientoCreation) {
        MovimientoModel objeto = movimientoService.guardar(movimientoCreation);
        return new ResponseEntity<>(movimientoMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<MovimientoDTO> borrar(@PathVariable(name = "id") Long id) {
        MovimientoModel objeto = movimientoService.eliminar(id);
        return new ResponseEntity<>(movimientoMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MovimientoDTO> reciclar(@PathVariable(name = "id") Long id) {
        MovimientoModel objeto = movimientoService.reciclar(id);
        return new ResponseEntity<>(movimientoMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        movimientoService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

