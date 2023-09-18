package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.UbicacionMapper;
import gloit.hiperionida.helios.mapper.creation.UbicacionCreation;
import gloit.hiperionida.helios.mapper.dto.UbicacionDTO;
import gloit.hiperionida.helios.model.UbicacionModel;
import gloit.hiperionida.helios.service.implementation.UbicacionServiceImpl;
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
@RequestMapping(value = "/api/ubicacion")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UbicacionController extends AbsBaseController {
    private final UbicacionServiceImpl ubicacionService;
    private final UbicacionMapper ubicacionMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el ubicacion. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<UbicacionDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        UbicacionModel objeto = ubicacionService.buscarPorId(id);
        return new ResponseEntity<>(ubicacionMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<UbicacionDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        UbicacionModel objeto = ubicacionService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(ubicacionMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<UbicacionDTO>> buscarTodas() {
        List<UbicacionModel> listado = ubicacionService.buscarTodas();
        ArrayList<UbicacionDTO> ubicacions = new ArrayList<>();
        for (UbicacionModel ubicacion:listado) {
            ubicacions.add(ubicacionMapper.toDto(ubicacion));
        }
        return new ResponseEntity<>(ubicacions, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<UbicacionDTO>> buscarTodasConEliminadas() {
        List<UbicacionModel> listado = ubicacionService.buscarTodasConEliminadas();
        ArrayList<UbicacionDTO> ubicacions = new ArrayList<>();
        for (UbicacionModel ubicacion:listado) {
            ubicacions.add(ubicacionMapper.toDto(ubicacion));
        }
        return new ResponseEntity<>(ubicacions, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<UbicacionDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<UbicacionModel> listado = ubicacionService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<UbicacionDTO> ubicacions = new ArrayList<>();
        for (UbicacionModel ubicacion:listado) {
            ubicacions.add(ubicacionMapper.toDto(ubicacion));
        }
        Slice slice = new SliceImpl(ubicacions, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<UbicacionDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<UbicacionModel> listado = ubicacionService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<UbicacionDTO> ubicacions = new ArrayList<>();
        for (UbicacionModel ubicacion:listado) {
            ubicacions.add(ubicacionMapper.toDto(ubicacion));
        }
        Slice slice = new SliceImpl(ubicacions, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= ubicacionService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= ubicacionService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<UbicacionDTO> guardar(@Valid @RequestBody UbicacionCreation ubicacionCreation) {
        UbicacionModel objeto = ubicacionService.guardar(ubicacionCreation);
        return new ResponseEntity<>(ubicacionMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<UbicacionDTO> borrar(@PathVariable(name = "id") Long id) {
        UbicacionModel objeto = ubicacionService.eliminar(id);
        return new ResponseEntity<>(ubicacionMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<UbicacionDTO> reciclar(@PathVariable(name = "id") Long id) {
        UbicacionModel objeto = ubicacionService.reciclar(id);
        return new ResponseEntity<>(ubicacionMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        ubicacionService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

