package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.ConductorMapper;
import gloit.hiperionida.helios.mapper.creation.ConductorCreation;
import gloit.hiperionida.helios.mapper.dto.ConductorDTO;
import gloit.hiperionida.helios.model.ConductorModel;
import gloit.hiperionida.helios.service.implementation.ConductorServiceImpl;
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
@RequestMapping(value = "/api/conductor")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ConductorController extends AbsBaseController {
    private final ConductorServiceImpl conductorService;
    private final ConductorMapper conductorMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el conductor. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ConductorDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        ConductorModel objeto = conductorService.buscarPorId(id);
        return new ResponseEntity<>(conductorMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConductorDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        ConductorModel objeto = conductorService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(conductorMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ConductorDTO>> buscarTodas() {
        List<ConductorModel> listado = conductorService.buscarTodas();
        ArrayList<ConductorDTO> conductors = new ArrayList<>();
        for (ConductorModel conductor:listado) {
            conductors.add(conductorMapper.toDto(conductor));
        }
        return new ResponseEntity<>(conductors, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ConductorDTO>> buscarTodasConEliminadas() {
        List<ConductorModel> listado = conductorService.buscarTodasConEliminadas();
        ArrayList<ConductorDTO> conductors = new ArrayList<>();
        for (ConductorModel conductor:listado) {
            conductors.add(conductorMapper.toDto(conductor));
        }
        return new ResponseEntity<>(conductors, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<ConductorDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<ConductorModel> listado = conductorService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<ConductorDTO> conductors = new ArrayList<>();
        for (ConductorModel conductor:listado) {
            conductors.add(conductorMapper.toDto(conductor));
        }
        Slice slice = new SliceImpl(conductors, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<ConductorDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<ConductorModel> listado = conductorService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<ConductorDTO> conductors = new ArrayList<>();
        for (ConductorModel conductor:listado) {
            conductors.add(conductorMapper.toDto(conductor));
        }
        Slice slice = new SliceImpl(conductors, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= conductorService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= conductorService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ConductorDTO> guardar(@Valid @RequestBody ConductorCreation conductorCreation) {
        ConductorModel objeto = conductorService.guardar(conductorCreation);
        return new ResponseEntity<>(conductorMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ConductorDTO> borrar(@PathVariable(name = "id") Long id) {
        ConductorModel objeto = conductorService.eliminar(id);
        return new ResponseEntity<>(conductorMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConductorDTO> reciclar(@PathVariable(name = "id") Long id) {
        ConductorModel objeto = conductorService.reciclar(id);
        return new ResponseEntity<>(conductorMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        conductorService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

