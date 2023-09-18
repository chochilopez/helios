package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.CajaMapper;
import gloit.hiperionida.helios.mapper.creation.CajaCreation;
import gloit.hiperionida.helios.mapper.dto.CajaDTO;
import gloit.hiperionida.helios.model.CajaModel;
import gloit.hiperionida.helios.service.implementation.CajaServiceImpl;
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
@RequestMapping(value = "/api/caja")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CajaController extends AbsBaseController {
    private final CajaServiceImpl cajaService;
    private final CajaMapper cajaMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el caja. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<CajaDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        CajaModel objeto = cajaService.buscarPorId(id);
        return new ResponseEntity<>(cajaMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CajaDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        CajaModel objeto = cajaService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(cajaMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CajaDTO>> buscarTodas() {
        List<CajaModel> listado = cajaService.buscarTodas();
        ArrayList<CajaDTO> cajas = new ArrayList<>();
        for (CajaModel caja:listado) {
            cajas.add(cajaMapper.toDto(caja));
        }
        return new ResponseEntity<>(cajas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CajaDTO>> buscarTodasConEliminadas() {
        List<CajaModel> listado = cajaService.buscarTodasConEliminadas();
        ArrayList<CajaDTO> cajas = new ArrayList<>();
        for (CajaModel caja:listado) {
            cajas.add(cajaMapper.toDto(caja));
        }
        return new ResponseEntity<>(cajas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<CajaDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CajaModel> listado = cajaService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CajaDTO> cajas = new ArrayList<>();
        for (CajaModel caja:listado) {
            cajas.add(cajaMapper.toDto(caja));
        }
        Slice slice = new SliceImpl(cajas, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<CajaDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CajaModel> listado = cajaService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CajaDTO> cajas = new ArrayList<>();
        for (CajaModel caja:listado) {
            cajas.add(cajaMapper.toDto(caja));
        }
        Slice slice = new SliceImpl(cajas, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= cajaService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= cajaService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CajaDTO> guardar(@Valid @RequestBody CajaCreation cajaCreation) {
        CajaModel objeto = cajaService.guardar(cajaCreation);
        return new ResponseEntity<>(cajaMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CajaDTO> borrar(@PathVariable(name = "id") Long id) {
        CajaModel objeto = cajaService.eliminar(id);
        return new ResponseEntity<>(cajaMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CajaDTO> reciclar(@PathVariable(name = "id") Long id) {
        CajaModel objeto = cajaService.reciclar(id);
        return new ResponseEntity<>(cajaMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        cajaService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

