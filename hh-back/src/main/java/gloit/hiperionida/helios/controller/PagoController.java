package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.PagoMapper;
import gloit.hiperionida.helios.mapper.creation.PagoCreation;
import gloit.hiperionida.helios.mapper.dto.PagoDTO;
import gloit.hiperionida.helios.model.PagoModel;
import gloit.hiperionida.helios.service.implementation.PagoServiceImpl;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.controller.AbsBaseController;
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
@RequestMapping(value = "/api/pago")
@RequiredArgsConstructor
@RestController
@Slf4j
public class PagoController extends AbsBaseController {
    private final PagoServiceImpl pagoService;
    private final PagoMapper pagoMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el pago. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<PagoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        PagoModel objeto = pagoService.buscarPorId(id);
        return new ResponseEntity<>(pagoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PagoDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        PagoModel objeto = pagoService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(pagoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<PagoDTO>> buscarTodas() {
        List<PagoModel> listado = pagoService.buscarTodas();
        ArrayList<PagoDTO> pagos = new ArrayList<>();
        for (PagoModel pago:listado) {
            pagos.add(pagoMapper.toDto(pago));
        }
        return new ResponseEntity<>(pagos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PagoDTO>> buscarTodasConEliminadas() {
        List<PagoModel> listado = pagoService.buscarTodasConEliminadas();
        ArrayList<PagoDTO> pagos = new ArrayList<>();
        for (PagoModel pago:listado) {
            pagos.add(pagoMapper.toDto(pago));
        }
        return new ResponseEntity<>(pagos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<PagoDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<PagoModel> listado = pagoService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<PagoDTO> pagos = new ArrayList<>();
        for (PagoModel pago:listado) {
            pagos.add(pagoMapper.toDto(pago));
        }
        Slice slice = new SliceImpl(pagos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<PagoDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<PagoModel> listado = pagoService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<PagoDTO> pagos = new ArrayList<>();
        for (PagoModel pago:listado) {
            pagos.add(pagoMapper.toDto(pago));
        }
        Slice slice = new SliceImpl(pagos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= pagoService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= pagoService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<PagoDTO> guardar(@Valid @RequestBody PagoCreation pagoCreation) {
        PagoModel objeto = pagoService.guardar(pagoCreation);
        return new ResponseEntity<>(pagoMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<PagoDTO> borrar(@PathVariable(name = "id") Long id) {
        PagoModel objeto = pagoService.eliminar(id);
        return new ResponseEntity<>(pagoMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PagoDTO> reciclar(@PathVariable(name = "id") Long id) {
        PagoModel objeto = pagoService.reciclar(id);
        return new ResponseEntity<>(pagoMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        pagoService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

