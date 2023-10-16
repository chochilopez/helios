package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.GastoMapper;
import gloit.hiperionida.helios.mapper.creation.GastoCreation;
import gloit.hiperionida.helios.mapper.dto.GastoDTO;
import gloit.hiperionida.helios.model.GastoModel;
import gloit.hiperionida.helios.service.implementation.GastoServiceImpl;
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
@RequestMapping(value = "/api/gasto")
@RequiredArgsConstructor
@RestController
@Slf4j
public class GastoController extends AbsBaseController {
    private final GastoServiceImpl gastoService;
    private final GastoMapper gastoMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el gasto. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<GastoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        GastoModel objeto = gastoService.buscarPorId(id);
        return new ResponseEntity<>(gastoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GastoDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        GastoModel objeto = gastoService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(gastoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<GastoDTO>> buscarTodas() {
        List<GastoModel> listado = gastoService.buscarTodas();
        ArrayList<GastoDTO> gastos = new ArrayList<>();
        for (GastoModel gasto:listado) {
            gastos.add(gastoMapper.toDto(gasto));
        }
        return new ResponseEntity<>(gastos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<GastoDTO>> buscarTodasConEliminadas() {
        List<GastoModel> listado = gastoService.buscarTodasConEliminadas();
        ArrayList<GastoDTO> gastos = new ArrayList<>();
        for (GastoModel gasto:listado) {
            gastos.add(gastoMapper.toDto(gasto));
        }
        return new ResponseEntity<>(gastos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<GastoDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<GastoModel> listado = gastoService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<GastoDTO> gastos = new ArrayList<>();
        for (GastoModel gasto:listado) {
            gastos.add(gastoMapper.toDto(gasto));
        }
        Slice slice = new SliceImpl(gastos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<GastoDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<GastoModel> listado = gastoService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<GastoDTO> gastos = new ArrayList<>();
        for (GastoModel gasto:listado) {
            gastos.add(gastoMapper.toDto(gasto));
        }
        Slice slice = new SliceImpl(gastos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= gastoService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= gastoService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<GastoDTO> guardar(@Valid @RequestBody GastoCreation gastoCreation) {
        GastoModel objeto = gastoService.guardar(gastoCreation);
        return new ResponseEntity<>(gastoMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<GastoDTO> borrar(@PathVariable(name = "id") Long id) {
        GastoModel objeto = gastoService.eliminar(id);
        return new ResponseEntity<>(gastoMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GastoDTO> reciclar(@PathVariable(name = "id") Long id) {
        GastoModel objeto = gastoService.reciclar(id);
        return new ResponseEntity<>(gastoMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        gastoService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

