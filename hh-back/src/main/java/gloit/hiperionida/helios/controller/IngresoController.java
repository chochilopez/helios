package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.IngresoMapper;
import gloit.hiperionida.helios.mapper.creation.IngresoCreation;
import gloit.hiperionida.helios.mapper.dto.IngresoDTO;
import gloit.hiperionida.helios.model.CiudadModel;
import gloit.hiperionida.helios.model.IngresoModel;
import gloit.hiperionida.helios.service.implementation.IngresoServiceImpl;
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
@RequestMapping(value = "/api/ingreso")
@RequiredArgsConstructor
@RestController
@Slf4j
public class IngresoController extends AbsBaseController {
    private final IngresoServiceImpl ingresoService;
    private final IngresoMapper ingresoMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el ingreso. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<IngresoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        IngresoModel objeto = ingresoService.buscarPorId(id);
        return new ResponseEntity<>(ingresoMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<IngresoDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        IngresoModel objeto = ingresoService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(ingresoMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<IngresoDTO>> buscarTodas() {
        List<IngresoModel> listado = ingresoService.buscarTodas();
        ArrayList<IngresoDTO> ingresos = new ArrayList<>();
        for (IngresoModel ingreso:listado) {
            ingresos.add(ingresoMapper.toDto(ingreso));
        }
        return new ResponseEntity<>(ingresos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<IngresoDTO>> buscarTodasConEliminadas() {
        List<IngresoModel> listado = ingresoService.buscarTodasConEliminadas();
        ArrayList<IngresoDTO> ingresos = new ArrayList<>();
        for (IngresoModel ingreso:listado) {
            ingresos.add(ingresoMapper.toDto(ingreso));
        }
        return new ResponseEntity<>(ingresos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<IngresoDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<IngresoModel> listado = ingresoService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<IngresoDTO> ingresos = new ArrayList<>();
        for (IngresoModel ingreso:listado) {
            ingresos.add(ingresoMapper.toDto(ingreso));
        }
        Slice slice = new SliceImpl(ingresos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<IngresoDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<IngresoModel> listado = ingresoService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<IngresoDTO> ingresos = new ArrayList<>();
        for (IngresoModel ingreso:listado) {
            ingresos.add(ingresoMapper.toDto(ingreso));
        }
        Slice slice = new SliceImpl(ingresos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= ingresoService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= ingresoService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<IngresoDTO> guardar(@Valid @RequestBody IngresoCreation ingresoCreation) {
        IngresoModel objeto = ingresoService.guardar(ingresoCreation);
        return new ResponseEntity<>(ingresoMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<IngresoDTO> borrar(@PathVariable(name = "id") Long id) {
        IngresoModel objeto = ingresoService.eliminar(id);
        return new ResponseEntity<>(ingresoMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<IngresoDTO> reciclar(@PathVariable(name = "id") Long id) {
        IngresoModel objeto = ingresoService.reciclar(id);
        return new ResponseEntity<>(ingresoMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        ingresoService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

